package com.example.cats.presentation.navigation.screenRoutes

sealed class Screen(val route: String) {
    data object Home : Screen("HomeScreen")
    data object Detail : Screen("DetailScreen")
}