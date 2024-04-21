package com.bcit.lab9yongeunkwon.data

class UserRepository(private val userDao: UserDAO) {

    fun insertEntity(user: LocalUser) {
        userDao.add(user)
    }

    fun getAll(): List<LocalUser> {
        return userDao.getAll()
    }

    fun deleteEntity(user: LocalUser){
        userDao.delete(user)
    }

}