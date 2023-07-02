package com.loki.fiat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.loki.fiat.presentation.AppState
import com.loki.fiat.presentation.detail.DetailsScreen
import com.loki.fiat.presentation.detail.DetailsViewModel
import com.loki.fiat.presentation.home.HomeScreen
import com.loki.fiat.presentation.home.HomeViewModel
import com.loki.fiat.presentation.search.SearchScreen
import com.loki.fiat.presentation.search.SearchViewModel
import com.loki.fiat.util.Constants.COIN_ID

@Composable
fun Navigation(
    appState: AppState
) {
    
    NavHost(navController = appState.navController, startDestination = Screens.HomeScreen.route) {

        composable(route = Screens.HomeScreen.route) {

            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = viewModel,
                openScreen = { appState.navigate(it) }
            )
        }

        composable(
            route = Screens.DetailScreen.withCoinId(),
            arguments = listOf(
                navArgument(name = COIN_ID) {
                    type = NavType.StringType
                }
            )
        ) {

            val viewModel = hiltViewModel<DetailsViewModel>()

            DetailsScreen(
                viewModel = viewModel,
                popScreen = { appState.popUp() }
            )
        }

        composable(route = Screens.SearchScreen.route) {

            val viewModel = hiltViewModel<SearchViewModel>()

            SearchScreen(
                viewModel = viewModel,
                openScreen = { appState.navigate(it) }
            )
        }
    }
}

sealed class Screens(val route: String) {
    object HomeScreen: Screens("home_screen")
    object DetailScreen: Screens("detail_screen")
    object SearchScreen: Screens("search_screen")

    fun withCoinId(): String {
        return "${DetailScreen.route}/{$COIN_ID}"
    }

    fun navWithArgs(coinId: String): String {
        return "${DetailScreen.route}/$coinId"
    }
}