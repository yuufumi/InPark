package com.example.inpark.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = false)
    var id:Int = 0,
    var nom:String,
    var prenom:String,
    var username:String,
    var email:String,
    var mot_de_passe:String,
    var num_telephone:String
)
