package com.example.tmdbmovieapp.ui.search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tmdbmovieapp.data.repository.MovieRepositoryImpl
import com.example.tmdbmovieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.cachedIn

class SearchViewModel : ViewModel() {

    private val repository = MovieRepositoryImpl()

    fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return repository.searchMovies(query).cachedIn(viewModelScope)
    }
}
