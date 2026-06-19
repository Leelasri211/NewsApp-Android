package com.leelasri.newsapp.di

import com.leelasri.newsapp.data.remote.NewsApi
import com.leelasri.newsapp.data.remote.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return RetrofitInstance.api
    }
}