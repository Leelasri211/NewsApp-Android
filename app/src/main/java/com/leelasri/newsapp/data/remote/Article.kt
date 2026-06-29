package com.leelasri.newsapp.data.remote

import com.leelasri.newsapp.ui.theme.data.local.ArticleEntity

data class Article(
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val sourceName: String?,
    val url: String?
)

fun Article.toEntity() = ArticleEntity(
    url = url ?: "",
    title = title,
    description = description,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    sourceName = sourceName,
)

fun ArticleEntity.toDomain() = Article(
    title = title,
    description = description,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    sourceName = sourceName,
    url = url
)