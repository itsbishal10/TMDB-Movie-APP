package com.example.tmdbmovieapp.ui.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
//import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.tmdbmovieapp.domain.model.Movie

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onMovieClick: (Int) -> Unit
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp)
    ) {items(movies.itemCount) { index ->
        val movie = movies[index]
        movie?.let {
            MovieItem(movie = it, onClick = onMovieClick)
        }
    }


        movies.apply {
            when {
                loadState.refresh is androidx.paging.LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.append is androidx.paging.LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is androidx.paging.LoadState.Error -> {
                    item { ErrorItem("Something went wrong") }
                }
            }
        }
    }
}
