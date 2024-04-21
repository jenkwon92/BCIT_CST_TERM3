package com.bcit.lab9yongeunkwon.ui.main

import androidx.compose.runtime.toMutableStateList
import com.bcit.lab9yongeunkwon.data.LocalUser
import com.bcit.lab9yongeunkwon.data.UserRepository

class UserState(private val repository: UserRepository) {

    var users = repository.getAll().toMutableStateList()

    fun add(localUser: LocalUser) {
        repository.insertEntity(localUser)
    }

    fun refresh() {
        users.apply {
            clear()
            addAll(repository.getAll())
        }
    }

    fun delete(localUser: LocalUser){
        users.remove(localUser)
        repository.deleteEntity(localUser)
    }
}