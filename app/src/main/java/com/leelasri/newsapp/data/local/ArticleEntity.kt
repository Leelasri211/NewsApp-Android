package com.leelasri.newsapp.ui.theme.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_articles")
data class ArticleEntity(
    @PrimaryKey val url: String,
    val title: String?,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val sourceName: String?,
    val savedAt: Long = System.currentTimeMillis()
)