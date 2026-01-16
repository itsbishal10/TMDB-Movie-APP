package com.example.tmdbmovieapp.data.mapper


import com.example.tmdbmovieapp.data.api.ApiConstants
import com.example.tmdbmovieapp.data.dto.MovieDto
import com.example.tmdbmovieapp.domain.model.Movie
import com.example.tmdbmovieapp.data.dto.MovieDetailsDto


fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = null,
        releaseDate = releaseDate,
        rating = rating,
        posterPath = posterPath
    )
}

fun MovieDetailsDto.toDomain(director: String?): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        rating = rating,
        posterPath = posterPath,
        director = director
    )
}

