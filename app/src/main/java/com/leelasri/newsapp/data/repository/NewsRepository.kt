package com.leelasri.newsapp.ui.theme.data.repository

import com.leelasri.newsapp.data.remote.Article
import com.leelasri.newsapp.data.remote.NewsApi
import com.leelasri.newsapp.data.remote.RetrofitInstance
import com.leelasri.newsapp.data.remote.toDomain
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi
){

//    private val api =  RetrofitInstance.api  // since we added hilt Inject - this line not needed any more

    suspend fun getArticles(): Result<List<Article>> {
        return try {
            val response = api.getTopHeadlines()
            val article = response.articles.map { it.toDomain() }
            Result.success(article)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}