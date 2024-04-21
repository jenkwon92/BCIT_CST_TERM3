package com.bcit.myminiapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user:LocalUser)

    @Query("SELECT * FROM user_table WHERE userName = :userName")
    fun findUser(userName:String) : LocalUser?
}