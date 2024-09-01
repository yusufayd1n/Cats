package com.example.cats.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.cats.data.remote.CatPagingSource
import com.example.cats.data.remote.api.ApiService
import com.example.cats.data.remote.models.CatResponse
import com.example.cats.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow

class CatsRepositoryImpl(
    private val apiService: ApiService
) : CatsRepository {
    override fun getCats(): Flow<PagingData<CatResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { CatPagingSource(apiService) }
        ).flow
    }
}