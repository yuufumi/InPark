package com.example.inpark.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.inpark.data.model.User

@Dao
interface UserDao {
    @Query("SELECT * from User")
    fun getAllUsers():List<User>

    @Query("SELECT * FROM User WHERE email=:email LIMIT 1")
    fun getUserByEmail(email:String):User?
    @Insert
    fun addUsers(vararg users: User)

    @Insert
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)
}
