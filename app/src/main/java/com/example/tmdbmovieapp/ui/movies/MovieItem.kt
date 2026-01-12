package com.example.tmdbmovieapp.ui.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tmdbmovieapp.domain.model.Movie
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MovieItem(
    movie: Movie,
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(movie.id) }
            .padding(6.dp)
    ) {

        AsyncImage(
            model = movie.posterUrl,
            contentDescription = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = movie.title,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = movie.releaseDate ?: "",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}
