package com.example.inpark.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Parking")
data class Parking(
    @PrimaryKey(autoGenerate = false)
    var id:Int,
    var nom:String,
    var photo:String,
    var description:String,
    var closing_hour:String,
    var latitude:Float,
    var location:String,
    var longitude:Float,
    var openning_hour:String,
    var price_per_hour:Float,
    var rating: Float,
    var status:Boolean,
    var sat: Boolean,
    var sun: Boolean,
    var mon: Boolean,
    var tue: Boolean,
    var wed: Boolean,
    var thu: Boolean,
    var fri: Boolean,
)
