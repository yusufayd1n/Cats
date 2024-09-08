package com.example.cats.domain.repository

import com.example.cats.data.remote.models.CatResponse
import com.example.cats.util.Resource
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    fun getCats(): Flow<Resource<List<CatResponse>>>
}