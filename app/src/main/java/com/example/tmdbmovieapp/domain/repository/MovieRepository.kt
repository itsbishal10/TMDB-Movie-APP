package com.example.tmdbmovieapp.domain.repository


import androidx.paging.PagingData
import com.example.tmdbmovieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<PagingData<Movie>>

    fun searchMovies(query: String): Flow<PagingData<Movie>>

    suspend fun getMovieDetails(movieId: Int): Movie
}
