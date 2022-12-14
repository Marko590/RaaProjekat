package com.marko590.raaprojekat.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.marko590.raaprojekat.model.database.UserDatabase
import com.marko590.raaprojekat.model.database.entities.UserTable
import com.marko590.raaprojekat.model.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel (application: Application) : AndroidViewModel(application) {

    val allUsers : LiveData<List<UserTable>>
    private val repository : UserRepository

    init {
        val dao = UserDatabase.getDatabase(application).getUserDao()
        repository = UserRepository(dao)
        allUsers = repository.allUsers
    }


    fun updateUser(user:UserTable) = viewModelScope.launch {
        repository.update(user)

    }



    fun fetchUsers(){
        viewModelScope.launch{
            repository.userDao.getAllByLiveData()
        }
    }

}