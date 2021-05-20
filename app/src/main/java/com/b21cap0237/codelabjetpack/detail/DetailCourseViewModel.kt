package com.b21cap0237.codelabjetpack.detail

import androidx.lifecycle.ViewModel
import com.b21cap0237.codelabjetpack.data.CourseEntity
import com.b21cap0237.codelabjetpack.data.ModuleEntity
import com.b21cap0237.codelabjetpack.data.source.AcademyRepository
import com.b21cap0237.codelabjetpack.utils.DataDummy

class DetailCourseViewModel (private val academyRepository: AcademyRepository): ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

//    fun getCourse(): CourseEntity {
//        lateinit var course: CourseEntity
//        val coursesEntities = DataDummy.generateDummyCourses()
//        for (courseEntity in coursesEntities) {
//            if (courseEntity.courseId == courseId) {
//                course = courseEntity
//            }
//        }
//        return course
//    }
//
//    fun getModules(): List<ModuleEntity> = DataDummy.generateDummyModules(courseId)

    fun getCourse(): CourseEntity = academyRepository.getCourseWithModules(courseId)

    fun getModules(): List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)
}