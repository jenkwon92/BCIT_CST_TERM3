package com.bcit.lab7yongeunkwon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Yongeun Kwon, A01263922
 */

data class Cartoon(val name:String, val imageId: Int)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val cartoonNames = stringArrayResource(id = R.array.cartoons)

            val cartoonList = listOf(
                Cartoon(cartoonNames[0], R.drawable.ahsoka),
                Cartoon(cartoonNames[1], R.drawable.bb8),
                Cartoon(cartoonNames[2], R.drawable.c3po),
                Cartoon(cartoonNames[3], R.drawable.chewbacca),
                Cartoon(cartoonNames[4], R.drawable.grogu),
                Cartoon(cartoonNames[5], R.drawable.jabba),
                Cartoon(cartoonNames[6], R.drawable.kilo),
                Cartoon(cartoonNames[7], R.drawable.trooper),
                Cartoon(cartoonNames[8], R.drawable.yoda)
            )

            val cartoonStateList = remember{
                mutableStateListOf(*cartoonList.toTypedArray())
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Box(modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                ){
                    Row(modifier = Modifier
                        .align(Alignment.TopCenter)
                    ){
                        Button(
                            modifier = Modifier
                                .padding(5.dp)
                                .width(200.dp),
                            shape = RoundedCornerShape(0.dp),
                            onClick = {
                                cartoonStateList.shuffle()
                            }
                        ) {
                            Text(
                                text = "Shuffle",
                                fontSize = 40.sp
                            )
                        }
                    }
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                ){
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        val rows = cartoonStateList.chunked(3)
                        rows.forEach { rowItems ->
                            item {
                                LazyRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentPadding = PaddingValues(horizontal = 16.dp)
                                ) {
                                    rowItems.forEach { cartoon ->
                                        item {
                                            CartoonCard(cartoon)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun CartoonCard(cartoon: Cartoon){
        var isExpanded by remember { mutableStateOf(false) }

        Card(
            modifier = Modifier
                .padding(12.dp)
                .clickable {
                    isExpanded = !isExpanded
                }
                .animateContentSize(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(if (isExpanded) 300.dp else 200.dp)
                    .clickable { isExpanded = !isExpanded },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                AnimatedVisibility(visible = !isExpanded) {
                    Text(
                        text = cartoon.name,
                        fontSize = 30.sp
                    )
                }
                CartoonImage(cartoon = cartoon, isExpanded = isExpanded)
            }
        }
    }

    @Composable
    fun CartoonImage(cartoon: Cartoon, isExpanded: Boolean) {
        Image(
            painter = painterResource(id = cartoon.imageId),
            contentDescription = "",
            modifier = Modifier
                .padding(vertical = 8.dp)
                .size(if (isExpanded) 300.dp else 200.dp)
                .animateContentSize()
        )
    }
}

