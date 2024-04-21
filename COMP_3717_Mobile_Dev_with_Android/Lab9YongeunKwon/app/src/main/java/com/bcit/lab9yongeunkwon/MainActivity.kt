package com.bcit.lab9yongeunkwon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import com.bcit.lab9yongeunkwon.ui.main.UserState
import com.bcit.lab9yongeunkwon.ui.main.MainContent

/**
 * Yongeun Kwon, A01263922
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userRepository = (application as MyApp).userRepository

        setContent {
            val userState = remember { UserState(userRepository) }
            MainContent(userState)
        }
    }
}
