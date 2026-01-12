package com.example.tmdbmovieapp.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
//import androidx.paging.compose.items
import com.example.tmdbmovieapp.ui.movies.ErrorItem
import com.example.tmdbmovieapp.ui.movies.LoadingItem
import com.example.tmdbmovieapp.ui.movies.MovieItem

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onMovieClick: (Int) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var searched by remember { mutableStateOf(false) }

    val searchResults =
        if (searched && query.isNotBlank()) {
            viewModel.searchMovies(query).collectAsLazyPagingItems()
        } else null

    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search movies") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { searched = true },
            modifier = Modifier.align(androidx.compose.ui.Alignment.End)
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(12.dp))

        searchResults?.let { movies ->
            LazyColumn {

                items(movies.itemCount) { index ->
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
                        loadState.refresh is androidx.paging.LoadState.Error -> {
                            item { ErrorItem("No results / Error occurred") }
                        }
                        loadState.append is androidx.paging.LoadState.Loading -> {
                            item { LoadingItem() }
                        }
                    }
                }
            }
        }
    }
}
