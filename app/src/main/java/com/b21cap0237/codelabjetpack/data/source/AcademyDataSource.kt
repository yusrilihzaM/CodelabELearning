package com.b21cap0237.codelabjetpack.data.source

import androidx.lifecycle.LiveData
import com.b21cap0237.codelabjetpack.data.CourseEntity
import com.b21cap0237.codelabjetpack.data.ModuleEntity

interface AcademyDataSource {

//    fun getAllCourses(): List<CourseEntity>
//
//    fun getBookmarkedCourses(): List<CourseEntity>
//
//    fun getCourseWithModules(courseId: String): CourseEntity
//
//    fun getAllModulesByCourse(courseId: String): List<ModuleEntity>
//
//    fun getContent(courseId: String, moduleId: String): ModuleEntity

    fun getAllCourses(): LiveData<List<CourseEntity>>

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>>

    fun getCourseWithModules(courseId: String): LiveData<CourseEntity>

    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>>

    fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity>
}