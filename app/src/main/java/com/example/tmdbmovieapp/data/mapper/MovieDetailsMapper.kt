package com.example.tmdbmovieapp.data.mapper

import com.example.tmdbmovieapp.data.dto.MovieDetailsDto
import com.example.tmdbmovieapp.domain.model.Movie

fun MovieDetailsDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        rating = rating,
        posterPath = posterPath
    )
}
