package com.loki.fiat.presentation

import androidx.navigation.NavHostController
import com.loki.fiat.presentation.navigation.Screens

class AppState (
    val navController: NavHostController
    ) {

    fun popUp() {
        navController.popBackStack()
    }


    fun navigate(route: String) {
        navController.navigate(route = route) {
            launchSingleTop = true
        }
    }

    fun bottomNavNavigate(route: String) {
        navController.navigate(route = route) {
            launchSingleTop = true
            popUpTo(Screens.HomeScreen.route) {
                saveState = true
            }
            restoreState = true
        }
    }
}