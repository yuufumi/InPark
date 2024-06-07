package com.example.inpark.repository

import android.util.Log
import com.example.inpark.data.api.AuthApi
import com.example.inpark.data.api.types.AuthRequest
import com.example.inpark.data.api.types.AuthResponse
import com.example.inpark.data.api.types.EmailRequest
import com.example.inpark.data.dao.UserDao
import com.example.inpark.data.model.User
import retrofit2.Response
import okhttp3.RequestBody

class AuthRepository(private val authApi: AuthApi,private val userDao: UserDao) {
    suspend fun registerUser(user: User): Response<User> {
        return authApi.registerUser(user)
    }

    suspend fun loginUser(user : AuthRequest):Response<User> {
        return  authApi.loginUser(user)
    }

    suspend fun getAllUsers():Response<List<User>>{
        return authApi.getAllUsers()
    }

    suspend fun deleteUser(id:String){
        userDao.deleteUser(id)
    }

    suspend fun getByEmail(email: String):Response<User>{
        return authApi.getByEmail(email)
    }

    suspend fun getById(id:String): User {
        return userDao.getUserById(id)!!
    }

    suspend fun addUser(user:User){
        Log.d("INSERTION VERIF", "youcef is here!!!")
        userDao.addUser(user)
    }
}