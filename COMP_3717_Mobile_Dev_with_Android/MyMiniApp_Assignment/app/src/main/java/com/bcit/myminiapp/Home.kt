package com.bcit.myminiapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import com.bcit.myminiapp.data.LocalUser
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Home(
//    navController: NavController,
//    amiiboState: AmiiboState,
    user: LocalUser
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hi, ${user.userName}\n Welcome to Amiibo!",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp),
            color = Color(0xFF6650a4),
            fontWeight = FontWeight.Bold
        )
        val logo = painterResource(R.drawable.amiibo_logo)
        Image(
            painter = logo,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 50.dp, 0.dp, 0.dp),
        )

        val image = painterResource(R.drawable.amiibo)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 30.dp, 0.dp, 0.dp),
        )
        Text("Find your amiibo and save it",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp),
            color = Color(0xFF6650a4),
            fontWeight = FontWeight.Bold
        )
    }
}
