package com.example.cats.data.repository

import com.example.cats.data.remote.api.ApiResult
import com.example.cats.data.remote.api.ApiService
import com.example.cats.data.remote.models.CatResponse
import com.example.cats.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatsRepositoryImpl(
    private val apiService: ApiService
) : CatsRepository {
    override fun getCats(): Flow<ApiResult<List<CatResponse>>> = flow {
        emit(ApiResult.Loading)
        try {
            emit(ApiResult.Success(apiService.getCats()))
        } catch (e: Exception) {
            emit(ApiResult.Error(e))
        }
    }
}