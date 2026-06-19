package com.leelasri.newsapp.ui.home

import com.leelasri.newsapp.data.remote.Article

data class HomeUiState (
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null
)
