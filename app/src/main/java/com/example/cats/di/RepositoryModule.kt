package com.example.cats.di

import com.example.cats.data.remote.api.ApiService
import com.example.cats.data.repository.CatsRepositoryImpl
import com.example.cats.domain.repository.CatsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideCatRepository(
        apiService: ApiService
    ): CatsRepository {
        return CatsRepositoryImpl(apiService)
    }
}