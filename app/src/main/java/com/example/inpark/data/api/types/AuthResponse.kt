package com.example.inpark.data.api.types

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String
)
