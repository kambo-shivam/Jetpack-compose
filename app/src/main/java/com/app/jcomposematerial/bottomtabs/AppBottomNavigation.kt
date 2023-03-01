package com.app.jcomposematerial.bottomtabs

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun AppBottomNavigation(navController: NavController) {
    val navItems = listOf(NavItems.Home, NavItems.FEED, NavItems.FAV, NavItems.PROFILE)

    BottomNavigation(backgroundColor = Color.DarkGray) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.forEach() { item ->
            BottomNavigationItem(
                selected = currentRoute == item.navRoute,
                label = { Text(text = stringResource(id = item.title)) },
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = "",
                    )
                },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Yellow.copy(alpha = 0.4f),
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.navRoute){
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}