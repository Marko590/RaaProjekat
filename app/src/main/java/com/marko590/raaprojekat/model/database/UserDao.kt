package com.marko590.raaprojekat.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.marko590.raaprojekat.model.database.entities.UserTable

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserTable)

    @Insert
    fun insertAll(users: List<UserTable>?)

    @Delete
    suspend fun delete(user: UserTable)

    @Delete
    fun deleteAll(users: List<UserTable>?)

    @Update
    suspend fun update(user: UserTable)

    @Query("SELECT * FROM UserTable")
    fun getAllByLiveData(): LiveData<List<UserTable>>

    @Query("SELECT * FROM UserTable WHERE email = :email")
    fun getByEmail(email: String): LiveData<UserTable>
}