package com.leelasri.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leelasri.newsapp.data.remote.RetrofitInstance
import com.leelasri.newsapp.ui.detail.DetailScreen
import com.leelasri.newsapp.ui.home.HomeScreen
import com.leelasri.newsapp.ui.home.HomeViewModel
import com.leelasri.newsapp.ui.saved.SavedScreen
import com.leelasri.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val homeViewModel: HomeViewModel = hiltViewModel()

                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                NavigationBarItem(
                                    selected = true,
                                    onClick = { navController.navigate("home") },
                                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                                    label = { Text("Home") }
                                )
                                NavigationBarItem(
                                    selected = false,
                                    onClick = { navController.navigate("saved") },
                                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Saved") },
                                    label = { Text("Saved") }
                                )
                            }
                        }
                    ) { padding ->
                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.padding(padding)
                        ) {
                            composable("home") {
                                HomeScreen(
                                    viewModel = homeViewModel,
                                    onArticleClick = { article ->
                                        homeViewModel.selectArticle(article)
                                        navController.navigate("detail")
                                    }
                                )
                            }
                            composable("saved") {
                                SavedScreen(
                                    onArticleClick = { article ->
                                        homeViewModel.selectArticle(article)
                                        navController.navigate("detail")
                                    }
                                )
                            }
                            composable("detail") {
                                val selected by homeViewModel.selectedArticle.collectAsStateWithLifecycle()
                                selected?.let {
                                    DetailScreen(article = it, onBackClick = { navController.popBackStack() })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}