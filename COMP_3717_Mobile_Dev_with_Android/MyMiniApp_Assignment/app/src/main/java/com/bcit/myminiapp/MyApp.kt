package com.bcit.myminiapp

import android.app.Application
import androidx.room.Room
import com.bcit.myminiapp.data.AppDataBase
import com.bcit.myminiapp.data.AmiiboRepository
import com.bcit.myminiapp.data.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

class MyApp : Application() {
    val client = HttpClient{
        install(ContentNegotiation){
            gson()
        }
    }

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "my-cool-database")
            .allowMainThreadQueries()
            .build()
    }

    val amiiboRepository by lazy {
        AmiiboRepository(client, db.userDao())
    }

    val userRepository by lazy { UserRepository(db.userDao())}

}