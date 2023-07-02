package com.loki.fiat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loki.fiat.presentation.AppState

@Composable
fun Navigation(
    appState: AppState
) {
    
    NavHost(navController = appState.navController, startDestination = Screens.HomeScreen.route) {

        composable(route = Screens.HomeScreen.route) {

        }

        composable(route = Screens.DetailScreen.route,) {

        }

        composable(route = Screens.SearchScreen.route) {

        }
    }
}

sealed class Screens(val route: String) {
    object HomeScreen: Screens("home_screen")
    object DetailScreen: Screens("detail_screen")
    object SearchScreen: Screens("search_screen")
}