package com.bcit.myminiapp.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bcit.myminiapp.data.LocalUser
import com.bcit.myminiapp.data.Screen

@Composable
fun Signin(navController: NavHostController, userState: UserState)  {
    val signInState = remember {SignInState()}
    var message  = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign in", fontSize = 40.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF625b71),
            modifier = Modifier.padding(50.dp)
        )
        Form(signInState)
        Text(
            message.value,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFF6650a4))
        Button(signInState, userState, navController, message)
    }
    if (userState.isLoggedIn) {
        LaunchedEffect(key1 = true) {
            navController.navigate(Screen.HOME.route)
        }
    }
}

@Composable
fun Form(signInState: SignInState) {
    Column (
        modifier = Modifier.padding(20.dp)
    ) {
        TextField(
            signInState.nameLabel,
            signInState.userName,
            signInState.onUserNameChanged
        )
        TextField(
            signInState.passwordLabel,
            signInState.password,
            signInState.onPasswordChanged
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    label:String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            modifier = Modifier
        )
        val containerColor = Color(0x1B3F51B5)
        TextField(
            value = value,
            onValueChange = {
                onValueChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
            )
        )
    }
}

@Composable
fun Button(
    signInState: SignInState,
    usersState: UserState,
    navController: NavHostController,
    message: MutableState<String>
) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
        horizontalArrangement = Arrangement.Center) {
        Button(
            modifier = Modifier.height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFF6650a4)),
            onClick = {
                signIn(signInState, usersState, navController, message)
            }
        ) {
            Text(text = "Sign in", fontSize = 25.sp, color = Color(0xFF6650a4))
        }
    }
}

fun signIn(
    signInState: SignInState,
    usersState: UserState,
    navController: NavHostController,
    message: MutableState<String>
) {
    if (signInState.userName.isNotEmpty() && signInState.password.isNotEmpty()) {
        var currentUser:LocalUser? = usersState.findUser(signInState.userName)
        if (currentUser == null) {
            currentUser = LocalUser(userName = signInState.userName, password = signInState.password, "")
            usersState.insertEntity(currentUser)
        }
        usersState.updateUser(currentUser)
        navController.navigate(Screen.HOME.route)
    } else {
        message.value = "Please enter valid user name and password"
    }
}