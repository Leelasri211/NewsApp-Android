package com.leelasri.newsapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leelasri.newsapp.ui.theme.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    private val _uiState  = MutableStateFlow<HomeUiState>(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchArticles()
    }

    fun fetchArticles() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = repository.getArticles()
            result.fold(
                onSuccess = { articles ->
                    _uiState.update {
                        it.copy(articles = articles, isLoading = false, error = null)
                    }
                },
                onFailure = { e ->
                    _uiState.update {
                        it.copy(isLoading = false, error = e.message ?: "Something went wrong")
                    }
                }
            )
        }
    }

    fun onRefresh() {
        Log.d("NewsApp", "Refresh triggered")
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true) }
            val result = repository.getArticles()
            result.fold(
                onSuccess = { articles ->
                    Log.d("NewsApp", "Refresh success — ${articles.size} articles")
                    _uiState.update {
                        it.copy(articles = articles, isRefreshing = false, error = null)
                    }
                },
                onFailure = { e ->
                    Log.e("NewsApp", "Refresh failed — ${e.message}")
                    _uiState.update {
                        it.copy(isRefreshing = false, error = e.message ?: "Refresh failed")
                    }
                }
            )
        }
    }
}