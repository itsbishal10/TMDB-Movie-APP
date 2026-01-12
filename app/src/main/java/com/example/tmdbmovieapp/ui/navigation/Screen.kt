package com.example.tmdbmovieapp.ui.navigation

sealed class Screen(val route: String) {
    object Movies : Screen("movies")
    object Search : Screen("search")
    object Details : Screen("details/{movieId}") {
        fun createRoute(movieId: Int) = "details/$movieId"
    }
}