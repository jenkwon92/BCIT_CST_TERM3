package com.bcit.lab6yongeunkwon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bcit.lab6yongeunkwon.ui.theme.Lab6YongeunKwonTheme

data class MyBoxData(val color: Color, val size:Int)

val boxDataList = listOf(
    MyBoxData(Color(0xFF3F51B5), 240),
    MyBoxData(Color(0xFFFF0707), 220),
    MyBoxData(Color(0xFF673AB7), 130),
    MyBoxData(Color(0xFFB37B91), 130),
    MyBoxData(Color(0xFF4CAF50), 130),
    MyBoxData(Color(0xFFFFEB3B), 130),
    MyBoxData(Color(0xFF5E3C1F), 130)

)

/**
 * Yongeun KWon A01263922
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting() {
    Surface(
        color = Color(0xFF6E859C),
        modifier = Modifier.fillMaxWidth()
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFF1630C4))
                        .size(210.dp)
                )
                Box(
                    modifier = Modifier
                        .background(Color(0xFFB30606))
                        .size(70.dp)
                )
            }
            Row(
                modifier = Modifier
                    .background(Color(0xFF8153D3))
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.End
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFE08788))
                            .size(110.dp)
                    )
                    Box(
                        modifier = Modifier
                        .background(Color(0xFF4CAF50))
                        .size(80.dp)
                    )
                    Box(
                       modifier = Modifier
                       .background(Color(0xFFEEC037))
                       .size(120.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(180.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFF805516))
                        .size(180.dp, 420.dp),
                        contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Lab 6",
                        color = Color(0xFFFFFFFF),
                        fontSize = 50.sp
                    )
                }
            }
        }
    }
}