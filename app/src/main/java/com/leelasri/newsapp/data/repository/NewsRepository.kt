package com.leelasri.newsapp.ui.theme.data.repository

import com.leelasri.newsapp.data.remote.Article
import com.leelasri.newsapp.data.remote.RetrofitInstance
import com.leelasri.newsapp.data.remote.toDomain

class NewsRepository {

    private val api =  RetrofitInstance.api

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