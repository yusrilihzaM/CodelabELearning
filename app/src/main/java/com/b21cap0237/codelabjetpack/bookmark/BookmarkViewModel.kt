package com.b21cap0237.codelabjetpack.bookmark

import androidx.lifecycle.ViewModel
import com.b21cap0237.codelabjetpack.data.CourseEntity
import com.b21cap0237.codelabjetpack.utils.DataDummy

class BookmarkViewModel: ViewModel(){
fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourses()
}