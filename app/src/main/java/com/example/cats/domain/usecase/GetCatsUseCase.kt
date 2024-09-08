package com.example.cats.domain.usecase

import com.example.cats.data.remote.models.CatResponse
import com.example.cats.domain.repository.CatsRepository
import com.example.cats.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(private val catsRepository: CatsRepository) {
    operator fun invoke(): Flow<Resource<List<CatResponse>>> {
        return catsRepository.getCats()
    }
}