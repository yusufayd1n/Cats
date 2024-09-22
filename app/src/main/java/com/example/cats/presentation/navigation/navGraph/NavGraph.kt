package com.example.cats.presentation.navigation.navGraph

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cats.presentation.navigation.screenRoutes.Screen
import com.example.cats.presentation.view.DetailScreen
import com.example.cats.presentation.view.HomeScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Detail.route) {
            DetailScreen()
        }
    }
}