package com.leelasri.newsapp.data.remote

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)

data class ArticleDto(
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val source: SourceDto?
)

data class SourceDto(
    val name: String?,
    val id: String?
)

fun ArticleDto.toDomain() = Article(
    title = title ?: "No Title",
    description = description ?: "No Description",
    urlToImage = urlToImage,
    publishedAt = publishedAt ?: "",
    sourceName = source?.name ?: "Unknown",
    url = url   // add this
)