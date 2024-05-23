package com.example.inpark.models

data class User(
    val id: Int,
    val username: String,
    val nom: String,
    val prenom: String,
    val email: String,
    val mot_de_passe:  String,
    val num_telephone: String
)
