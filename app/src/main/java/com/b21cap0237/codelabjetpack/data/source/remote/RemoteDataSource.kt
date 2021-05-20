package com.b21cap0237.codelabjetpack.data.source.remote

import android.os.Handler
import android.os.Looper
import com.b21cap0237.codelabjetpack.data.source.remote.response.ContentResponse
import com.b21cap0237.codelabjetpack.data.source.remote.response.CourseResponse
import com.b21cap0237.codelabjetpack.data.source.remote.response.ModuleResponse
import com.b21cap0237.codelabjetpack.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    private val handler = Handler(Looper.getMainLooper())
    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }
    fun getAllCourses(callback: LoadCoursesCallback) {
        handler.postDelayed({ callback.onAllCoursesReceived(jsonHelper.loadCourses()) }, SERVICE_LATENCY_IN_MILLIS)
    }
    fun getModules(courseId: String, callback: LoadModulesCallback) {
        handler.postDelayed({ callback.onAllModulesReceived(jsonHelper.loadModule(courseId)) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getContent(moduleId: String, callback: LoadContentCallback) {
        handler.postDelayed({ callback.onContentReceived(jsonHelper.loadContent(moduleId)) }, SERVICE_LATENCY_IN_MILLIS)
    }
//    fun getAllCourses(): List<CourseResponse> = jsonHelper.loadCourses()
//
//    fun getModules(courseId: String): List<ModuleResponse> = jsonHelper.loadModule(courseId)
//
//    fun getContent(moduleId: String): ContentResponse = jsonHelper.loadContent(moduleId)

    interface LoadCoursesCallback {
        fun onAllCoursesReceived(courseResponses: List<CourseResponse>)
    }
    interface LoadModulesCallback {
        fun onAllModulesReceived(moduleResponses: List<ModuleResponse>)
    }
    interface LoadContentCallback {
        fun onContentReceived(contentResponse: ContentResponse)
    }
}