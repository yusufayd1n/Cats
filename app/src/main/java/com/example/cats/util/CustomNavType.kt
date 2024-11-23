package com.example.cats.util

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.example.cats.data.remote.models.CatResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {

    val CatType = object : NavType<CatResponse> (
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): CatResponse? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): CatResponse {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: CatResponse): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: CatResponse) {
            bundle.putString(key, Json.encodeToString(value))
        }

    }
}