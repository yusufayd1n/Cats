package com.example.cats.domain.repository

import com.example.cats.data.remote.api.ApiResult
import com.example.cats.data.remote.models.CatResponse
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    fun getCats(): Flow<ApiResult<List<CatResponse>>>
}