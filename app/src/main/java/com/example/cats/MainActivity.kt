package com.example.cats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.example.cats.data.remote.models.CatResponse
import com.example.cats.presentation.viewmodel.HomeViewModel
import com.example.cats.ui.theme.CatsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatsTheme {
                CatListScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun CatItem(cat: CatResponse) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        cat.image?.url?.let {
            Image(
                painter = rememberImagePainter(data = it),
                contentDescription = "Cat Image",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
        }


        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = cat.name, style = MaterialTheme.typography.headlineLarge)

            Text(text = cat.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun CatListScreen(viewModel: HomeViewModel) {
    val cats = viewModel.getAllCats.collectAsLazyPagingItems()

    LazyColumn {
        items(cats.itemCount) { index ->
            val cat = cats[index]
            cat?.let { CatItem(it) }
        }

        cats.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }
                loadState.append is LoadState.Loading -> {
                    item { CircularProgressIndicator() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = cats.loadState.refresh as LoadState.Error
                    item { Text("Error: ${e.error.localizedMessage}") }
                }
                loadState.append is LoadState.Error -> {
                    val e = cats.loadState.append as LoadState.Error
                    item { Text("Error: ${e.error.localizedMessage}") }
                }
            }
        }
    }
}
