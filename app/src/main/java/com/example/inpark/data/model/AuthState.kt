package com.example.inpark.data.model

import com.example.inpark.data.model.User

data class AuthState(
        val isLoading : Boolean = false,
        val responseMsg : String = "",
        val activeUser : User? = null
    )
