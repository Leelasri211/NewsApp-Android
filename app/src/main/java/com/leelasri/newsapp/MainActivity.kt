package com.leelasri.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.leelasri.newsapp.ui.theme.NewsAppTheme
import com.leelasri.newsapp.ui.theme.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
      lifecycleScope.launch {
          try {
              val response = RetrofitInstance.api.getTopHeadlines()
              Log.d("NewsApp","title: ${response.totalResults}")
              response.articles.forEach { article ->
                  Log.d("NewsApp","Title: ${article.title}")
              }
          }catch (e: Exception){
              Log.e("NewsApp","Error: ${e.message}")
          }
      }
        setContent {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Check Logcat For API Response!")
            }
        }
    }
}