package com.bcit.myminiapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bcit.myminiapp.data.Amiibo
import com.bcit.myminiapp.data.LocalUser
import com.bcit.myminiapp.data.Screen
import com.bcit.myminiapp.ui.main.AmiiboState
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Locale


@Composable

fun Details(
    gameSeries: String,
    name: String,
    image: String,
    release: String,
    navController: NavController,
    favoriteState: AmiiboState,
    user: LocalUser,
    amiiboState: AmiiboState
) {
    val amiibo: Amiibo? = amiiboState.findAmiiboByName(name, amiiboState.amiibolist)

    val isFavorite = favoriteState.favorites.contains(amiibo)

    val formattedDates = parseAndFormatReleaseDates(release)
    val verticalSpacing = 16.dp
    val horizontalSpacing = 16.dp

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(10.dp)
        ){
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
        }
        Row{
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .padding(horizontal = horizontalSpacing)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(vertical = verticalSpacing)
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = horizontalSpacing)
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = Color.Red,
                            modifier = Modifier
                                .size(35.dp)
                                .clickable {
                                    amiibo?.let { nonNullAmiibo ->
                                        if (!isFavorite) {
                                            favoriteState.addFavoriteName(user,nonNullAmiibo)
                                            favoriteState.refreshFavoriteList(nonNullAmiibo.name,amiiboState)
                                            navController.navigate(Screen.FAVORITE.route)
                                        } else {
                                            favoriteState.favorites.remove(nonNullAmiibo)
                                            favoriteState.removeFavoriteName(user, nonNullAmiibo.name)
                                            favoriteState.refreshFavoriteList(nonNullAmiibo.name,amiiboState)
                                        }
                                    }
                                }
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = verticalSpacing),
                    ){
                        Text(
                            text = gameSeries,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(vertical = verticalSpacing)
                        )
                        Text(
                            text = name,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                            modifier = Modifier.padding(vertical = verticalSpacing)
                        )
                        AsyncImage(
                            model = image,
                            contentDescription = null,
                            modifier = Modifier.size(250.dp)
                        )
                        Text(
                            text = formattedDates,
                            textAlign = TextAlign.Start,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(vertical = verticalSpacing)
                        )
                    }
                }
            }
        }
    }
}

fun parseAndFormatReleaseDates(releaseString: String): String {
    val jsonObject = JSONObject(releaseString)

    val auDate = jsonObject.getString("au")
    val euDate = jsonObject.getString("eu")
    val jpDate = jsonObject.getString("jp")
    val naDate = jsonObject.getString("na")

    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val auFormattedDate = SimpleDateFormat("yyyy MMMM dd", Locale.getDefault()).format(formatter.parse(auDate)!!)
    val euFormattedDate = SimpleDateFormat("yyyy MMMM dd", Locale.getDefault()).format(formatter.parse(euDate)!!)
    val jpFormattedDate = SimpleDateFormat("yyyy MMMM dd", Locale.getDefault()).format(formatter.parse(jpDate)!!)
    val naFormattedDate = SimpleDateFormat("yyyy MMMM dd", Locale.getDefault()).format(formatter.parse(naDate)!!)

    return "Australia $auFormattedDate\nEurope $euFormattedDate\nJapan $jpFormattedDate\nNorth America $naFormattedDate"
}