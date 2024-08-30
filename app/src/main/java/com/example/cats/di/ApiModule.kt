package com.example.cats.di

import com.example.cats.MainApplication
import com.example.cats.R
import com.example.cats.data.remote.api.ApiService
import com.example.cats.data.remote.api.okhttp.AppOkHTTPClient
import com.example.cats.data.remote.api.okhttp.OkHTTPClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        application: MainApplication,
        okHttpClient: OkHTTPClientImpl
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(application.getString(R.string.base_url))
            .client(okHttpClient.getOkHTTPClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}