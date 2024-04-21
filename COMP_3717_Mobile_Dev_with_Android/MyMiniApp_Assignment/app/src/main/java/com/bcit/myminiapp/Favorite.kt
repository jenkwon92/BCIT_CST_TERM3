package com.bcit.myminiapp

import android.util.Log
import androidx.compose.runtime.Composable
import com.bcit.myminiapp.ui.main.AmiiboState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bcit.myminiapp.data.LocalUser
import com.bcit.myminiapp.data.Screen
import java.net.URLEncoder

@Composable
fun Favorite(
    navController: NavHostController,
    favoriteState: AmiiboState,
) {
    if (favoriteState.favorites.size == 0) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Here is no favorite amiibos yet!",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(30.dp, 100.dp, 30.dp, 50.dp)
            )
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("My Favorite amiibos", fontSize = 25.sp,
                color = Color(0xFF6650a4),
                modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 10.dp),
            )
            LazyColumn(
                modifier = Modifier
                    .background(Color.White)
            ) {
                items(favoriteState.favorites.size) {
                    val amiiboSeries = favoriteState.favorites[it].amiiboSeries
                    val character = favoriteState.favorites[it].character
                    val image = favoriteState.favorites[it].image
                    val gameSeries = favoriteState.favorites[it].gameSeries
                    val name = favoriteState.favorites[it].name
                    val release = favoriteState.favorites[it].release.toString()

                    // To avoid issues, since slashes can be interpreted as path separators in navigation paths,
                    // URLs need to be encoded.
                    val encodedImage = URLEncoder.encode(image, "UTF-8")
                    ListItem(
                        modifier = Modifier.clickable {
                            navController.navigate("details/${gameSeries}/${name}/${encodedImage}/${release}")
                        },
                        headlineContent = { Text(text = character) },
                        supportingContent = { Text(text = amiiboSeries) },
                        leadingContent = {
                            AsyncImage(
                                model = image,
                                contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )
                        },
                        trailingContent = { Text("▶") }
                    )
                }
            }
        }
    }
}