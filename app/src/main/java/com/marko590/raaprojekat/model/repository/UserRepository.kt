package com.marko590.raaprojekat.model.repository

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.marko590.raaprojekat.model.database.UserDao
import com.marko590.raaprojekat.model.database.entities.UserTable


class UserRepository(private val userDao: UserDao) {

    // on below line we are creating a variable for our list
    // and we are getting all the notes from our DAO class.
    val allUsers: LiveData<List<UserTable>> = userDao.getAllByLiveData()

    // on below line we are creating an insert method
    // for adding the note to our database.
    suspend fun insert(user:UserTable) {
        userDao.insert(user)
    }
    suspend fun singleUser(email:String) {
        userDao.getByEmail(email)
    }
    // on below line we are creating a delete method
    // for deleting our note from database.
    suspend fun delete(user:UserTable){
        userDao.delete(user)
    }

    // on below line we are creating a update method for
    // updating our note from database.
    suspend fun update(user:UserTable){
        userDao.update(user)
    }

}