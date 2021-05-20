package com.b21cap0237.codelabjetpack.di

import android.content.Context
import com.b21cap0237.codelabjetpack.data.source.AcademyRepository
import com.b21cap0237.codelabjetpack.data.source.remote.RemoteDataSource
import com.b21cap0237.codelabjetpack.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }
}