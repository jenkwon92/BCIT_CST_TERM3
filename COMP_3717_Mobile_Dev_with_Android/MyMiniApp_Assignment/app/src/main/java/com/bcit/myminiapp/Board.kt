package com.bcit.myminiapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.bcit.myminiapp.ui.main.AmiiboState
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.net.URLEncoder

@Composable
fun Board(navController: NavController, amiiboState: AmiiboState){
    var checked by remember { mutableStateOf(true) }
    Column{
        Row(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top,
        ){
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                thumbContent = if (checked) {
                    { Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else { null }
            )
        }
        Row{
            if(!checked){
                LazyColumn{
                    items(amiiboState.amiibolist.size) {
                        val amiiboSeries = amiiboState.amiibolist[it].amiiboSeries
                        val character = amiiboState.amiibolist[it].character
                        val image = amiiboState.amiibolist[it].image
                        val gameSeries = amiiboState.amiibolist[it].gameSeries
                        val name = amiiboState.amiibolist[it].name
                        val release = amiiboState.amiibolist[it].release.toString()

                        // To avoid issues, since slashes can be interpreted as path separators in navigation paths,
                        // URLs need to be encoded.
                        val encodedImage = URLEncoder.encode(image, "UTF-8")
                        ListItem(
                            modifier = Modifier.clickable {
                                navController.navigate("details/${gameSeries}/${name}/${encodedImage}/${release}")
                            },
                            headlineContent = { Text(text = character) },
                            supportingContent = {Text(text = amiiboSeries)},
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
            }else{
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Adaptive(200.dp),
                    verticalItemSpacing = 4.dp,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    content = {
                        items(amiiboState.amiibolist.size) {
                            val image = amiiboState.amiibolist[it].image
                            val gameSeries = amiiboState.amiibolist[it].gameSeries
                            val name = amiiboState.amiibolist[it].name
                            val release = amiiboState.amiibolist[it].release.toString()
                            val encodedImage = URLEncoder.encode(image, "UTF-8")

                            AsyncImage(
                                model = image,
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth().wrapContentHeight()
                                    .clickable {
                                        navController.navigate("details/${gameSeries}/${name}/${encodedImage}/${release}")
                                    }
                            )
                        }
                    }
                )
            }
        }
    }
}