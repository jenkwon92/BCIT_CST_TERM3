package com.bcit.myminiapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class LocalUser (
    @PrimaryKey val userName : String,
    val password:String?,
    var favoriteAmiiboNames:String
)