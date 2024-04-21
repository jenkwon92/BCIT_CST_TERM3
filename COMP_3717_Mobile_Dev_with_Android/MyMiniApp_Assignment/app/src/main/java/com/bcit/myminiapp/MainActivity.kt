package com.bcit.myminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.bcit.myminiapp.ui.main.AmiiboState
import com.bcit.myminiapp.ui.main.MainContent
import com.bcit.myminiapp.ui.main.UserState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val amiiboRepository = (application as MyApp).amiiboRepository
        val userRepository = (application as MyApp).userRepository


        setContent {
            val amiiboState = AmiiboState(amiiboRepository)
            val favoriteState = remember { AmiiboState(amiiboRepository) }
            val userState = remember{ UserState(userRepository) }

            LaunchedEffect(true){
                amiiboState.getAmiibo()
            }

            MainContent(amiiboState, userState, favoriteState)
        }
    }
}