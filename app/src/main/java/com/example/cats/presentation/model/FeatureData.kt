package com.example.cats.presentation.model

import androidx.compose.ui.graphics.Color

data class FeatureData(
    val text: String,
    val icon: Int,
    val iconDescription: String,
    val iconTint: Color,
    val widthFraction: Float
)
