package com.example.tmdbmovieapp.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieDetailViewModel = viewModel()
) {
    val movie by viewModel.movie.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }

    when (val data = movie) {
        null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                AsyncImage(
                    model = data.posterUrl,
                    contentDescription = data.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = data.title,
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Release Date: ${data.releaseDate ?: "N/A"}")
                Text("Rating: ${data.rating}")

                //Director
                Spacer(modifier = Modifier.height(6.dp))
                Text("Director: ${data.director ?: "N/A"}")

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = data.overview ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
