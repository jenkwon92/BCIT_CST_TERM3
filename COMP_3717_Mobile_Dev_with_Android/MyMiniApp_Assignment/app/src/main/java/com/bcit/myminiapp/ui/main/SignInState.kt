package com.bcit.myminiapp.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SignInState {
    // name text field
    val nameLabel:String = "User name"
    var userName by mutableStateOf("")
    val onUserNameChanged:(String) -> Unit = {
        userName = it
        validUserName = userName.isNotEmpty()
    }
    var validUserName = false

    // password text field
    val passwordLabel = "Password"
    var password by mutableStateOf("")
    val onPasswordChanged:(String) -> Unit = {
        password = it
        validPassword = password.isNotEmpty()
    }
    var validPassword = false
}