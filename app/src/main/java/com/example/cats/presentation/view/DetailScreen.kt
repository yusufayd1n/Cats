package com.example.cats.presentation.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cats.data.remote.models.CatResponse

@Composable
fun DetailScreen(cat: CatResponse?) {
    Text(cat?.name.toString())
}

