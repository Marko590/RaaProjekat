package com.marko590.raaprojekat.model.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

import androidx.room.ColumnInfo


// on below line we are specifying our table name
@Entity(tableName = "userTable")

// on below line we are specifying our column info
// and inside that we are passing our column name
class UserTable (@ColumnInfo(name = "firstname")val firstname :String,
            @ColumnInfo(name = "lastname")val lastname :String,
            @ColumnInfo(name = "email")val email :String,
                 @ColumnInfo(name = "password")val password :String,
                 @ColumnInfo(name = "preferredCuisine")val preferredCuisine :String,
                 @PrimaryKey(autoGenerate = true) var id :Int)

