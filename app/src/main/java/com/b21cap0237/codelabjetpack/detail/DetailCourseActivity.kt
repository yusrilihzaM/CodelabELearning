package com.b21cap0237.codelabjetpack.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.b21cap0237.codelabjetpack.R
import com.b21cap0237.codelabjetpack.data.CourseEntity
import com.b21cap0237.codelabjetpack.databinding.ActivityDetailCourseBinding
import com.b21cap0237.codelabjetpack.databinding.ContentDetailCourseBinding
import com.b21cap0237.codelabjetpack.reader.CourseReaderActivity
import com.b21cap0237.codelabjetpack.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class DetailCourseActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    private lateinit var detailContentBinding: ContentDetailCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailCourseBinding = ActivityDetailCourseBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailCourseBinding.detailContent

        setContentView(activityDetailCourseBinding.root)

        setSupportActionBar(activityDetailCourseBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailCourseViewModel::class.java]
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailCourseViewModel::class.java]
        val adapter = DetailCourseAdapter()

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
//                val modules = DataDummy.generateDummyModules(courseId)
                viewModel.setSelectedCourse(courseId)
                val modules = viewModel.getModules()
                activityDetailCourseBinding.progressBar.visibility = View.VISIBLE
                activityDetailCourseBinding.content.visibility = View.INVISIBLE
                viewModel.getModules().observe(this, { modules ->
                    activityDetailCourseBinding.progressBar.visibility = View.GONE
                    activityDetailCourseBinding.content.visibility = View.VISIBLE
                    adapter.setModules(modules)
                    adapter.notifyDataSetChanged()
                })
                viewModel.getCourse().observe(this, { course -> populateCourse(course) })
//                populateCourse(viewModel.getCourse() as CourseEntity)
//                for (course in DataDummy.generateDummyCourses()) {
//                    if (course.courseId == courseId) {
//                        populateCourse(course)
//                    }
//                }
            }
        }

        with(detailContentBinding.rvModule) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateCourse(courseEntity: CourseEntity) {
        detailContentBinding.textTitle.text = courseEntity.title
        detailContentBinding.textDescription.text = courseEntity.description
        detailContentBinding.textDate.text = resources.getString(R.string.deadline_date, courseEntity.deadline)

        Glide.with(this)
            .load(courseEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding.imagePoster)

        detailContentBinding.btnStart.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            startActivity(intent)
        }
    }
}