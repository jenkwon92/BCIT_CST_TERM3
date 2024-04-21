package com.bcit.myminiapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [LocalUser::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDAO
//    abstract fun favoriteDao(): FavoriteDAO
}