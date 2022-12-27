@file:Suppress("unused")

package com.marko590.raaprojekat.model.repository

import androidx.lifecycle.LiveData
import com.marko590.raaprojekat.model.database.UserDao
import com.marko590.raaprojekat.model.database.entities.UserTable


class UserRepository(val userDao: UserDao) {

    val allUsers: LiveData<List<UserTable>> = userDao.getAllByLiveData()


    suspend fun insert(user:UserTable) {
        userDao.insert(user)
    }

    suspend fun delete(user:UserTable){
        userDao.delete(user)
    }

    suspend fun update(user:UserTable){
        userDao.update(user)
    }

}