package com.example.tmdbmovieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.tmdbmovieapp.data.api.RetrofitClient
import com.example.tmdbmovieapp.data.mapper.toDomain
import com.example.tmdbmovieapp.data.paging.MoviePagingSource
import com.example.tmdbmovieapp.domain.model.Movie
import com.example.tmdbmovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl : MovieRepository {

    private val api = RetrofitClient.api

    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(api)
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(api, query)
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Movie {
        //Movie details
        val details = api.getMovieDetails(movieId)

        //Movie credits
        val credits = api.getMovieCredits(movieId)

        //Extract director
        val director = credits.crew
            .firstOrNull { it.job == "Director" }
            ?.name

        //Map to domain
        return details.toDomain(director)
    }
}
