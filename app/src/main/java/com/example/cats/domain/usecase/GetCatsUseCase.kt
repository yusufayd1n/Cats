package com.example.cats.domain.usecase

import com.example.cats.data.remote.api.ApiResult
import com.example.cats.data.remote.models.CatResponse
import com.example.cats.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(private val catsRepository: CatsRepository) {
    operator fun invoke(): Flow<ApiResult<List<CatResponse>>> {
        return catsRepository.getCats()
    }
}