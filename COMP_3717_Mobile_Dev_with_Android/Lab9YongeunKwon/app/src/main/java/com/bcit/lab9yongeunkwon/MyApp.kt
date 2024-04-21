package com.bcit.lab9yongeunkwon

import android.app.Application
import androidx.room.Room
import com.bcit.lab9yongeunkwon.data.AppDataBase
import com.bcit.lab9yongeunkwon.data.UserRepository

class MyApp : Application(){

    private val db by lazy {

        Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "my-cool-database")
            .allowMainThreadQueries()
            .build()
    }

    val userRepository by lazy {
        UserRepository(db.userDao())
    }
}