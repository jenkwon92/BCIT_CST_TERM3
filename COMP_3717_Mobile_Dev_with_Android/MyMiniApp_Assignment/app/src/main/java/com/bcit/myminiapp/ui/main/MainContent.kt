package com.bcit.myminiapp.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bcit.myminiapp.Board
import com.bcit.myminiapp.Details
import com.bcit.myminiapp.Favorite
import com.bcit.myminiapp.Home
import com.bcit.myminiapp.MyNavigationBar
import com.bcit.myminiapp.MyTopBar
import com.bcit.myminiapp.data.Amiibo
import com.bcit.myminiapp.data.Screen

@Composable
fun MainContent(
    amiiboState: AmiiboState,
    userState: UserState,
    favoriteState: AmiiboState
) {
    val navController = rememberNavController()
    favoriteState.refreshFavoriteList(userState.user.favoriteAmiiboNames, amiiboState)
    Scaffold(
        topBar = {
            MyTopBar()
        },
        bottomBar = {
            if (userState.isLoggedIn) {
                MyNavigationBar(navController = navController, userState.user)
            }
        }
    ) {it ->
        NavHost(
            navController = navController,
            startDestination = Screen.SIGNIN.route,
            modifier = Modifier.padding(it)
        ){
            // Screen 1
            composable(Screen.SIGNIN.route){
                Signin(navController, userState)
            }

            // Screen 2
            composable(Screen.HOME.route){
                Home(userState.user)
            }

            // Screen 3
            composable(Screen.BOARD.route){
                Board(navController, amiiboState)
            }

            //screen 4
            composable("details/{gameSeries}/{name}/{image}/{release}"){
                // By default, arguments are String
                val gameSeries = it.arguments?.getString("gameSeries") ?: ""
                val name = it.arguments?.getString("name") ?: ""
                val image = it.arguments?.getString("image") ?: ""
                val release = it.arguments?.getString("release") ?: ""

                Details(gameSeries, name, image, release, navController, amiiboState, userState.user, amiiboState)
            }

            // Screen 5
            composable(Screen.FAVORITE.route){
                Favorite(navController, favoriteState)
            }
        }
    }
}