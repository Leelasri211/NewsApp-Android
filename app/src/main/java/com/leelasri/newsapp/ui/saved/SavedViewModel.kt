package com.leelasri.newsapp.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leelasri.newsapp.data.remote.Article
import com.leelasri.newsapp.ui.theme.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    val savedArticles: StateFlow<List<Article>> = repository.getSavedArticles()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun unsaveArticle(url: String) {
        viewModelScope.launch {
            repository.unsaveArticle(url)
        }
    }
}