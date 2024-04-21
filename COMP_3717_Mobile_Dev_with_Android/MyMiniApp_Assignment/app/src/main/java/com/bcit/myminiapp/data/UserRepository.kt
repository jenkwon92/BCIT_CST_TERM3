package com.bcit.myminiapp.data

class UserRepository(private val userDao: UserDAO) {
    fun insertEntity(user: LocalUser) {
        userDao.insert(user)
    }

    fun findUser(userName : String) : LocalUser? {
        return userDao.findUser(userName)
    }
}