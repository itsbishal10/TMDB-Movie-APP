package com.example.tmdbmovieapp.data.mapper


import com.example.tmdbmovieapp.data.api.ApiConstants
import com.example.tmdbmovieapp.data.dto.MovieDto
import com.example.tmdbmovieapp.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterUrl = posterPath?.let {
            ApiConstants.IMAGE_BASE_URL + it
        },
        releaseDate = releaseDate,
        rating = rating
    )
}
