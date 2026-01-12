package com.example.tmdbmovieapp.data.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tmdbmovieapp.data.api.TmdbApiService
import com.example.tmdbmovieapp.data.dto.MovieDto

class MoviePagingSource(
    private val api: TmdbApiService,
    private val query: String? = null
) : PagingSource<Int, MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        return try {
            val page = params.key ?: 1

            val response = if (query.isNullOrEmpty()) {
                api.getMovies(page)
            } else {
                api.searchMovies(query, page)
            }

            LoadResult.Page(
                data = response.movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page < response.totalPages) page + 1 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}
