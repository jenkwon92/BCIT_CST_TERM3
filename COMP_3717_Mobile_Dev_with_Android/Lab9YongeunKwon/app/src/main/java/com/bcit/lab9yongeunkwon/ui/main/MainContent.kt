package com.bcit.lab9yongeunkwon.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcit.lab9yongeunkwon.data.LocalUser

@Composable
fun MainContent(userState: UserState) {
    val signupState = remember { SignupState() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            "uid::",
            signupState.uid.toString(),
            signupState.onUidChanged,
        )

        CustomTextField(
            "name::",
            signupState.name,
            signupState.onNameChanged
        )

        CustomTextField(
            "email::",
            signupState.email,
            signupState.onEmailChanged,
        )
        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Button(
                onClick = {
                    userState.add(
                        LocalUser(
                            uid = signupState.uid,
                            name = signupState.name,
                            email = signupState.email
                        )
                    )
                },
                modifier = Modifier
                    .size(width = 120.dp, height = 60.dp)
            ) {
                Text("Add", fontSize = 20.sp)
            }

            Button(
                onClick = {
                    userState.refresh()
            },
                modifier = Modifier
                    .size(width = 160.dp, height = 60.dp)
            ) {
                Text("Refresh", fontSize = 20.sp)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn {
            items(userState.users.size) {
                UserItem(userState.users[it], userState, signupState)
            }
        }
    }
}

@Composable
fun UserItem(user: LocalUser, userState: UserState, signupState: SignupState){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                signupState.uid = user.uid
                signupState.name = user.name.toString()
                signupState.email = user.email.toString()
            }
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0XFFE7E0EB))
            .height(80.dp)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(user.name!!, fontSize = 20.sp)
        Text(user.email!!, fontSize = 20.sp)
        IconButton(
            onClick = { userState.delete(user) }) {
            Icon(Icons.Rounded.Close, contentDescription = null)
        }
    }
}

@Composable
fun CustomTextField(label:String, value: String?, onValueChanged: (String) -> Unit) {

    Text(text = label)
    TextField(
        value = if (value != "null") value ?: "" else "",
        onValueChange = onValueChanged,
        modifier = Modifier.fillMaxWidth()
    )
}
