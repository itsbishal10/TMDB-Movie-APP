package com.example.tmdbmovieapp.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String?,
    val releaseDate: String?,
    val rating: Double,
    val posterPath: String?,
    val director: String? = null
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"
}
