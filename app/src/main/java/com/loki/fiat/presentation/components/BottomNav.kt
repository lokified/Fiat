package com.loki.fiat.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.loki.fiats.presentation.navigation.Screens

@Composable
fun BottomNav(
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val bottomBarDestination = navItems.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar {

            navItems.forEach { bottomNavItem ->

                val selected = bottomNavItem.route == backStackEntry?.destination?.route

                NavigationBarItem(
                    selected = selected,
                    onClick = { onItemClick(bottomNavItem) },
                    icon = {
                        Icon(
                            imageVector = bottomNavItem.icon,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = bottomNavItem.navTitle)
                    }
                )
            }
        }
    }
}

data class BottomNavItem(
    val icon: ImageVector,
    val navTitle: String,
    val route: String
)


val navItems = listOf(

    BottomNavItem(
        icon = Icons.Filled.Home,
        navTitle = "Home",
        route = Screens.HomeScreen.route
    ),
    BottomNavItem(
        icon = Icons.Filled.Search,
        navTitle = "Search",
        route = Screens.SearchScreen.route
    )
)