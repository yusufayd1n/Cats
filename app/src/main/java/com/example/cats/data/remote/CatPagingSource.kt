package com.example.cats.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cats.data.remote.api.ApiService
import com.example.cats.data.remote.models.CatResponse

class CatPagingSource(private val apiService: ApiService) : PagingSource<Int, CatResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatResponse> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getCats(limit = params.loadSize, page = page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatResponse>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }
}