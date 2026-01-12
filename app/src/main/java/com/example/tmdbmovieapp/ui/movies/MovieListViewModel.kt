package com.example.tmdbmovieapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tmdbmovieapp.data.repository.MovieRepositoryImpl
import com.example.tmdbmovieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.cachedIn

class MovieListViewModel : ViewModel() {

    private val repository = MovieRepositoryImpl()

    val movies: Flow<PagingData<Movie>> =
        repository.getMovies().cachedIn(viewModelScope)
}
