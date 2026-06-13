package com.leelasri.newsapp.ui.theme.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi{
    @GET("v2/everything")
    suspend fun getTopHeadlines(
        @Query("q") country: String = "android",
        @Query("apiKey") apiKey: String = "your_api"): NewsResponse
}