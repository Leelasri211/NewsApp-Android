package com.leelasri.newsapp.ui.saved

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leelasri.newsapp.data.remote.Article
import com.leelasri.newsapp.ui.home.ArticleCard

@Composable
fun SavedScreen(
    viewModel: SavedViewModel = hiltViewModel(),
    onArticleClick: (Article) -> Unit
) {
    val savedArticles by viewModel.savedArticles.collectAsStateWithLifecycle()

    if (savedArticles.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No saved articles yet")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(savedArticles) { article ->
                ArticleCard(
                    article = article,
                    onClick = { onArticleClick(article) }
                )
            }
        }
    }
}