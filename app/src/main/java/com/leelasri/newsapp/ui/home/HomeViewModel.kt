package com.leelasri.newsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leelasri.newsapp.ui.theme.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val repository = NewsRepository()
    private val _uiState  = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
     val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchArticles()
    }

    fun fetchArticles() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            val result = repository.getArticles()
            _uiState.value = result.fold(
                onSuccess = {
                    HomeUiState.Success(it)
                },
                onFailure = {
                    HomeUiState.Error(it.message ?: "Something went wrong")
                }

            )
        }
    }
}