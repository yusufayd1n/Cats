package com.example.cats.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.cats.data.remote.models.CatResponse

@Composable
fun HomeScreen(
    cats: List<CatResponse>
) {
    LazyColumn {
        items(cats) { cat ->
            CatItem(cat)
        }
    }
}

@Composable
fun CatItem(cat: CatResponse) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(cat.image?.url),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .padding(4.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .height(128.dp)
                .padding(start = 4.dp)
        ) {
            Text(text = cat.name, style = MaterialTheme.typography.headlineMedium)
            Text(
                text = cat.description, style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}