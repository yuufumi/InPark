package com.example.inpark.repository

import com.example.inpark.data.api.AuthApi
import com.example.inpark.data.api.types.AuthRequest
import com.example.inpark.data.api.types.AuthResponse
import com.example.inpark.data.model.User
import retrofit2.Response
import okhttp3.RequestBody

class AuthRepository(private val authApi: AuthApi) {
    suspend fun registerUser(user: User): Response<User> {
        return authApi.registerUser(user)
    }

    suspend fun loginUser(user : AuthRequest):Response<User> {
        return  authApi.loginUser(user)
    }

    suspend fun getAllUsers():Response<List<User>>{
        return authApi.getAllUsers()
    }
}