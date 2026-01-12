package com.example.tmdbmovieapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tmdbmovieapp.domain.model.Movie
import com.example.tmdbmovieapp.domain.repository.MovieRepository
import com.example.tmdbmovieapp.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.flow.*

class MovieListViewModel : ViewModel() {

    private val repository: MovieRepository = MovieRepositoryImpl()

    // Search text
    private val searchQuery = MutableStateFlow("")

    // Movies stream (popular OR search)
    val movies: Flow<PagingData<Movie>> =
        searchQuery
            .debounce(300)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                if (query.isBlank()) {
                    repository.getMovies()        // Popular movies
                } else {
                    repository.searchMovies(query)
                }
            }
            .cachedIn(viewModelScope)

    // Call this from UI
    fun onSearchQueryChange(query: String) {
        searchQuery.value = query
    }
}
