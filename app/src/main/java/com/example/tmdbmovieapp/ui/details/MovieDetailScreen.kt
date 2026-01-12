package com.example.tmdbmovieapp.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieDetailViewModel
) {
    val movie by viewModel.movie.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    movie?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            AsyncImage(
                model = it.posterUrl,
                contentDescription = it.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = it.title,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Release Date: ${it.releaseDate ?: "N/A"}")
            Text("Rating: ${it.rating}")

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = it.overview ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
