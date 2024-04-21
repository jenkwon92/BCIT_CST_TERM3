package com.bcit.myminiapp.data

enum class ApiEndpoints(val url:String) {
    BASE_URL("https://www.amiiboapi.com/api"),
    AMIIBOS("${BASE_URL.url}/amiibo/"),

    FIELD("${AMIIBOS.url}?name=amiibo_name")

}