package com.bcit.myminiapp.data

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class AmiiboRepository(private val client: HttpClient, private val userDao: UserDAO) {
    suspend fun getAmiibo(): Amiibos{
        try{
            val response = client.get(ApiEndpoints.AMIIBOS.url)

            val json = response.body<JsonObject>().toString()

            return deserializeJson(json)
        } catch (e: Exception) {
            Log.e("Repository error", "Exception in LaunchedEffect: $e")
            throw e
        }
    }

    private fun deserializeJson(json:String): Amiibos{

        return Gson().fromJson(json, Amiibos::class.java)
    }

    fun updateFavouriteNames(user:LocalUser) {
        userDao.insert(user)
    }
}