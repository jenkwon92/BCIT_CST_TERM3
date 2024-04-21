package com.bcit.myminiapp.data

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(val icon: ImageVector, val route:String)

enum class Screen(val route:String){
    SIGNIN("signin"),
    HOME("home"),
    BOARD("board"),
    FAVORITE("favorite"),
}