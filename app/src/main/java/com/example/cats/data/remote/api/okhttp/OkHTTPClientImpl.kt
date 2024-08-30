package com.example.cats.data.remote.api.okhttp


import com.example.cats.BuildConfig
import okhttp3.OkHttpClient
import javax.inject.Inject

class OkHTTPClientImpl @Inject constructor() : AppOkHTTPClient {

    override fun getOkHTTPClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestWithHeaders = originalRequest.newBuilder()
                    .addHeader("x-api-key", BuildConfig.xApiKey)
                    .build()
                chain.proceed(requestWithHeaders)
            }
            .build()
    }
}