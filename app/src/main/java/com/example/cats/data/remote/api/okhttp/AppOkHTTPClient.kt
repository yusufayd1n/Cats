package com.example.cats.data.remote.api.okhttp

import okhttp3.OkHttpClient

interface AppOkHTTPClient {
    fun getOkHTTPClient(): OkHttpClient
}