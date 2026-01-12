package com.example.tmdbmovieapp.ui.details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovieapp.data.repository.MovieRepositoryImpl
import com.example.tmdbmovieapp.domain.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {

    private val repository = MovieRepositoryImpl()

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            _movie.value = repository.getMovieDetails(movieId)
        }
    }
}
