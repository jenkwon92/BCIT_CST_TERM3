package com.bcit.myminiapp.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bcit.myminiapp.data.LocalUser
import com.bcit.myminiapp.data.UserRepository

class UserState (private val userRepository: UserRepository) {
    var user by mutableStateOf(LocalUser("", null, ""))
    var isLoggedIn by mutableStateOf(false)
        private set
    fun updateUser(user: LocalUser?) {
        this.user = user ?: LocalUser("", null, "")
        isLoggedIn = user != null
    }

    fun insertEntity(user: LocalUser) {
        userRepository.insertEntity(user)
    }
    fun findUser(userName: String): LocalUser? {
        return userRepository.findUser(userName)
    }

}