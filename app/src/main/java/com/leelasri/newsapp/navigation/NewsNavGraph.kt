package com.leelasri.newsapp.navigation

sealed class Screen(val root: String) {
    object Home : Screen("home")
    object Detail : Screen("detail")
}