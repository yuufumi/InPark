package com.example.inpark.models

data class SignInResult(
    val data: com.example.inpark.models.UserData?,
    val errorMessage: String?
            )

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)