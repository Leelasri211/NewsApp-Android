package com.leelasri.newsapp.ui.theme.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM saved_articles ORDER BY savedAt DESC")
    fun getAllSaved(): Flow<List<ArticleEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM saved_articles WHERE url = :url)")
    fun isArticleSaved(url: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: ArticleEntity)

    @Query("DELETE FROM saved_articles WHERE url = :url")
    suspend fun deleteArticle(url: String)
}