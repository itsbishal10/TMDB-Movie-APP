package com.example.tmdbmovieapp.ui.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tmdbmovieapp.domain.model.Movie

@Composable
fun MovieGridItem(
    movie: Movie,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable { onClick(movie.id) }
    ) {
        Column {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )

            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(6.dp),
                maxLines = 2
            )
        }
    }
}
