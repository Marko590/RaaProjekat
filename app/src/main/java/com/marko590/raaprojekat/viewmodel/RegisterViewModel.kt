package com.marko590.raaprojekat.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.marko590.raaprojekat.model.database.UserDatabase
import com.marko590.raaprojekat.model.database.entities.UserTable
import com.marko590.raaprojekat.model.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel (application: Application) : AndroidViewModel(application) {

    val allUsers : LiveData<List<UserTable>>
    private val repository : UserRepository

    init {
        val dao = UserDatabase.getDatabase(application).getUserDao()
        repository = UserRepository(dao)
        allUsers = repository.allUsers
    }

    fun fetchUsers(){
        viewModelScope.launch{
            repository
        }
    }

    fun addUser(user:UserTable) = viewModelScope.launch{
        repository.insert(user)
    }
}