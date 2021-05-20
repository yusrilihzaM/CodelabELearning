package com.b21cap0237.codelabjetpack.academy

import androidx.lifecycle.ViewModel
import com.b21cap0237.codelabjetpack.data.CourseEntity
import com.b21cap0237.codelabjetpack.data.source.AcademyRepository
import com.b21cap0237.codelabjetpack.utils.DataDummy

class AcademyViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    fun getCourses(): List<CourseEntity> = academyRepository.getAllCourses()
}