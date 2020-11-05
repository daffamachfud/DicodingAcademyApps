package com.onoh.academy.di

import android.content.Context
import com.onoh.academy.data.source.AcademyRepository
import com.onoh.academy.data.source.local.LocalDataSource
import com.onoh.academy.data.source.local.room.AcademyDatabase
import com.onoh.academy.data.source.remote.RemoteDataSource
import com.onoh.academy.utils.AppExecutors
import com.onoh.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}