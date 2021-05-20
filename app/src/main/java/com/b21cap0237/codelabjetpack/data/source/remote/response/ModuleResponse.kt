package com.b21cap0237.codelabjetpack.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModuleResponse(
    var moduleId: String,
    var courseId: String,
    var title: String,
    var position: Int
):Parcelable
