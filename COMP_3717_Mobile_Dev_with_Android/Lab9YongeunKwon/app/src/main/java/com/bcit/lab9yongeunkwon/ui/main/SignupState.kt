package com.bcit.lab9yongeunkwon.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

class SignupState {

    var uid by mutableStateOf<Int?>(null)
    var onUidChanged:(String) -> Unit = {
        uid = it.toIntOrNull()
    }

    var name by mutableStateOf("")
    var onNameChanged:(String) -> Unit = {
        name = it
    }

    var email by mutableStateOf("")
    var onEmailChanged:(String) -> Unit = {
        email = it
    }
}


