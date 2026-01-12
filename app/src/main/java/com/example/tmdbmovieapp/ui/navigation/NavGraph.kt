package com.example.tmdbmovieapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tmdbmovieapp.ui.details.MovieDetailScreen
import com.example.tmdbmovieapp.ui.details.MovieDetailViewModel
import com.example.tmdbmovieapp.ui.movies.MovieListScreen
import com.example.tmdbmovieapp.ui.movies.MovieListViewModel
import com.example.tmdbmovieapp.ui.search.SearchScreen
import com.example.tmdbmovieapp.ui.search.SearchViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Movies.route
    ) {

        composable(Screen.Movies.route) {
            MovieListScreen(
                viewModel = MovieListViewModel(),
                onMovieClick = {
                    navController.navigate(Screen.Details.createRoute(it))
                }
            )
        }

        composable(Screen.Search.route) {
            SearchScreen(
                viewModel = SearchViewModel(),
                onMovieClick = {
                    navController.navigate(Screen.Details.createRoute(it))
                }
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailScreen(
                movieId = movieId,
                viewModel = MovieDetailViewModel()
            )
        }
    }
}
