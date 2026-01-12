package com.example.tmdbmovieapp.data.dto

import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    val page: Int,
    @SerializedName("results") val movies: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int
)
