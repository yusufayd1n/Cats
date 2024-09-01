package com.example.cats.data.remote.api

import com.example.cats.data.remote.models.CatResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("breeds")
    suspend fun getCats(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): List<CatResponse>
}