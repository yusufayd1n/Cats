package com.example.cats.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("cats")
    fun getCats()
}