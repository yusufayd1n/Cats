package com.example.cats.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cats.data.remote.models.CatResponse
import com.example.cats.presentation.navigation.screenRoutes.Screen
import com.example.cats.presentation.state.UIState
import com.example.cats.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val catsState by viewModel.allCats.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getAllCats()
    }

    when (val data = catsState) {
        is UIState.Loading -> LoadingScreen()
        is UIState.Success -> {
            data.data.let { cats ->
                LazyColumn {
                    items(cats) { cat ->
                        CatItem(cat) {
                            navController.navigate(Screen.Detail.route)
                        }
                    }
                }
            }
        }

        is UIState.Error -> ErrorScreen(Modifier, data.message)
    }
}

@Composable
fun CatItem(cat: CatResponse, onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .clickable {
            onClick.invoke()
        }) {
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
                text = cat.description,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}