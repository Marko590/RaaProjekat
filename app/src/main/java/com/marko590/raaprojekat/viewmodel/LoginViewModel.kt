package com.marko590.raaprojekat.viewmodel

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.marko590.raaprojekat.model.database.UserDatabase
import com.marko590.raaprojekat.model.database.entities.UserTable
import com.marko590.raaprojekat.model.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel (application: Application) : AndroidViewModel(application) {

    // on below line we are creating a variable
    // for our all notes list and repository
    val allUsers : LiveData<List<UserTable>>
    val repository : UserRepository

    // on below line we are initializing
    // our dao, repository and all notes
    init {
        val dao = UserDatabase.getDatabase(application).getUserDao()
        repository = UserRepository(dao)
        allUsers = repository.allUsers
    }

    // on below line we are creating a new method for deleting a note. In this we are
    // calling a delete method from our repository to delete our note.
    fun deleteUser (user:UserTable) = viewModelScope.launch {
        repository.delete(user)
    }

    // on below line we are creating a new method for updating a note. In this we are
    // calling a update method from our repository to update our note.
    fun updateUser(user:UserTable) = viewModelScope.launch {
        repository.update(user)
    }
    fun getByEmail(email:String) = viewModelScope.launch {
         repository.singleUser(email)
    }

    fun fetchUsers(){
        viewModelScope.launch{
            repository
        }
    }
    // on below line we are creating a new method for adding a new note to our database
    // we are calling a method from our repository to add a new note.
    fun addUser(user:UserTable) = viewModelScope.launch{
        repository.insert(user)
    }
}