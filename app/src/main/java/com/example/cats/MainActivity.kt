package com.example.cats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.cats.presentation.navigation.navGraph.SetUpNavGraph
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
                val navController = rememberNavController()
                SetUpNavGraph(navController)
            }
        }
    }

}


