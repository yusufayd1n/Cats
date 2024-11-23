package com.example.cats.presentation.navigation.screenRoutes

import com.example.cats.data.remote.models.CatResponse
import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

@Serializable
data class DetailScreenRoute(
    val cat: CatResponse
)