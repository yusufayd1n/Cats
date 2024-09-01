package com.example.cats.domain.repository

import androidx.paging.PagingData
import com.example.cats.data.remote.models.CatResponse
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    fun getCats(): Flow<PagingData<CatResponse>>
}