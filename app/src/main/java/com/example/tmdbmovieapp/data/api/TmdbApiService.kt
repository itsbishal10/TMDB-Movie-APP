package com.example.tmdbmovieapp.data.api

import com.example.tmdbmovieapp.data.dto.MovieCreditsDto
import com.example.tmdbmovieapp.data.dto.MovieDetailsDto
import com.example.tmdbmovieapp.data.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String= "popularity.desc"
    ): MovieResponseDto

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResponseDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailsDto

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int
    ): MovieCreditsDto

}
