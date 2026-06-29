package com.leelasri.newsapp.ui.theme.data.repository

import com.leelasri.newsapp.data.remote.Article
import com.leelasri.newsapp.data.remote.NewsApi
import com.leelasri.newsapp.data.remote.RetrofitInstance
import com.leelasri.newsapp.data.remote.toDomain
import com.leelasri.newsapp.data.remote.toEntity
import com.leelasri.newsapp.ui.theme.data.local.ArticleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi,
    private val dao: ArticleDao
) {

//    private val api =  RetrofitInstance.api  // since we added hilt Inject - this line not needed any more

    suspend fun getArticles(forceRefresh: Boolean = false): Result<List<Article>> {
        return try {
            val page = if (forceRefresh) (1..3).random() else 1
            val response = api.getTopHeadlines(page = page)
            Result.success(response.articles.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun saveArticle(article: Article) {
        dao.saveArticle(article.toEntity())
    }

    suspend fun unsaveArticle(url: String) {
        dao.deleteArticle(url)
    }

    fun isArticleSaved(url: String): Flow<Boolean> {
        return dao.isArticleSaved(url)
    }

    fun getSavedArticles(): Flow<List<Article>> {
        return dao.getAllSaved().map { list -> list.map { it.toDomain() } }
    }
}