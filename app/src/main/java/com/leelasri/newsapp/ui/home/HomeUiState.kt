package com.leelasri.newsapp.ui.home

import com.leelasri.newsapp.data.remote.Article

sealed class HomeUiState {
    object Loading: HomeUiState()
    data class Success(val article: List<Article>): HomeUiState()
    data class Error(val message: String): HomeUiState()
}