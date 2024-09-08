package com.example.cats.data.repository

import com.example.cats.data.remote.api.ApiService
import com.example.cats.data.remote.models.CatResponse
import com.example.cats.domain.repository.CatsRepository
import com.example.cats.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatsRepositoryImpl(
    private val apiService: ApiService
) : CatsRepository {
    override fun getCats(): Flow<Resource<List<CatResponse>>> = flow {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(apiService.getCats()))
        } catch (e: Exception) {
            emit(Resource.error(e.message.toString(),null))
        }
    }
}