package com.example.cats.presentation.navigation.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.cats.data.remote.models.CatResponse
import com.example.cats.presentation.navigation.screenRoutes.DetailScreenRoute
import com.example.cats.presentation.navigation.screenRoutes.HomeScreenRoute
import com.example.cats.presentation.view.DetailScreen
import com.example.cats.presentation.view.HomeScreen
import com.example.cats.util.CustomNavType
import kotlin.reflect.typeOf

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute
    ) {
        composable<HomeScreenRoute> {
            HomeScreen(navController)
        }

        composable<DetailScreenRoute>(
            typeMap = mapOf(
                typeOf<CatResponse>() to CustomNavType.CatType
            )
        ) { backStackEntry ->
            val args = backStackEntry.toRoute<DetailScreenRoute>()
            DetailScreen(args.cat)
        }
    }
}