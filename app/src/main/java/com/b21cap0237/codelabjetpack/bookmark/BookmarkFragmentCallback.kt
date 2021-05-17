package com.b21cap0237.codelabjetpack.bookmark

import com.b21cap0237.codelabjetpack.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}