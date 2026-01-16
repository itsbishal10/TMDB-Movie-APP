package com.example.tmdbmovieapp.data.dto

import com.google.gson.annotations.SerializedName

data class MovieCreditsDto(
    val crew: List<CrewDto>
)

data class CrewDto(
    val id: Int,
    val name: String,
    val job: String
)
