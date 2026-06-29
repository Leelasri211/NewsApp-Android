package com.leelasri.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leelasri.newsapp.ui.theme.data.local.ArticleDao
import com.leelasri.newsapp.ui.theme.data.local.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}