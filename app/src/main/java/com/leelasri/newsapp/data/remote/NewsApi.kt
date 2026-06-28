package com.leelasri.newsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi{
    @GET("v2/everything")
    suspend fun getTopHeadlines(
        @Query("q") query: String = "android",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("pageSize") pageSize: Int = 30,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = "YOUR_KEY"
    ): NewsResponse
}