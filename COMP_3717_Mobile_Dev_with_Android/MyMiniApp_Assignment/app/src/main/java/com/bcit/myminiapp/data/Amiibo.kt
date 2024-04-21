package com.bcit.myminiapp.data

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class Amiibos(
    val amiibo: List<Amiibo>
)

data class Amiibo(
    val amiiboSeries: String,
    val character: String,
    val gameSeries: String,
    val head: String,
    val image: String,
    val name: String,
    val release: JsonObject,
    val tail: String,
    val type: String,
)

