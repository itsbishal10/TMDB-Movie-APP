package com.example.tmdbmovieapp.ui.movies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.foundation.lazy.grid.items
import com.example.tmdbmovieapp.domain.model.Movie
import com.example.tmdbmovieapp.ui.common.FullScreenLoading
import com.example.tmdbmovieapp.ui.common.ErrorState
import com.example.tmdbmovieapp.ui.common.EmptyState
import androidx.compose.foundation.lazy.grid.GridItemSpan



@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onMovieClick: (Int) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }



    val movies = viewModel.movies.collectAsLazyPagingItems()



    Column(modifier = Modifier.fillMaxSize()) {

        /* ---------------- SEARCH BAR ---------------- */

        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.onSearchQueryChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search movies") }
        )

        /* ---------------- GRID VIEW ---------------- */

        Box(modifier = Modifier.fillMaxSize()) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(movies.itemCount) { index ->
                    val movie = movies[index]
                    movie?.let {
                        MovieItem(
                            movie = it,
                            onClick = onMovieClick
                        )
                    }
                }

                // Pagination loading (bottom)
                if (movies.loadState.append is LoadState.Loading) {
                    item(span = { GridItemSpan(3) }) {
                        LoadingItem()
                    }
                }
            }


            /* ---------------- STATES ---------------- */

            // Initial loading
            if (movies.loadState.refresh is LoadState.Loading) {
                FullScreenLoading()
            }

            // Error state
            if (movies.loadState.refresh is LoadState.Error) {
                ErrorState(
                    message = "Something went wrong",
                    onRetry = { movies.retry() }
                )
            }

            // Empty state
            if (
                movies.loadState.refresh is LoadState.NotLoading &&
                movies.itemCount == 0
            ) {
                EmptyState()
            }
        }
    }
}
