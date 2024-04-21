package com.bcit.myminiapp

import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bcit.myminiapp.data.LocalUser
import com.bcit.myminiapp.data.NavItem
import com.bcit.myminiapp.data.Screen


val items = listOf(
    NavItem(Icons.Rounded.Home, Screen.HOME.route),
    NavItem(Icons.Rounded.List, Screen.BOARD.route),
    NavItem(Icons.Rounded.Favorite, Screen.FAVORITE.route)
)

@Composable
fun MyNavigationBar(navController: NavController, user: LocalUser?){
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach {
            NavigationBarItem(
                label = { Text(it.route) },
                selected = currentRoute == it.route,
                onClick = {
                    navController.navigate(it.route)
                },
                icon = {
                    Icon(it.icon, contentDescription = null)
                }
            )
        }
    }
}
