package com.example.cats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cats.presentation.view.ErrorScreen
import com.example.cats.presentation.view.HomeScreen
import com.example.cats.presentation.view.LoadingScreen
import com.example.cats.presentation.viewmodel.HomeViewModel
import com.example.cats.ui.theme.CatsTheme
import com.example.cats.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@Serializable
data object CatListRoute

/*@Serializable
data class CatDetailRoute(
    val cat: CatResponse
)*/

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatsTheme {
                LaunchedEffect(Unit) {
                    viewModel.getAllCats()
                }

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = CatListRoute
                ) {
                    composable<CatListRoute> {
                        ObserveCats()
                    }

                    /*composable<CatDetailRoute> {
                        //val arguments = it.toRoute<CatDetailRoute>()
                    }*/
                }
            }
        }
    }

    @Composable
    fun ObserveCats() {
        val catsState by viewModel.allCats.observeAsState()
        when (catsState?.status) {
            Status.LOADING -> {
                LoadingScreen()
            }

            Status.ERROR -> {
                ErrorScreen(errorMessage = catsState?.message.toString())
            }

            Status.SUCCESS -> {
                catsState?.data?.let { cats -> HomeScreen(cats) }
            }

            null -> {

            }
        }
    }
}


