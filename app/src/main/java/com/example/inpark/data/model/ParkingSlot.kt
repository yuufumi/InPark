package com.example.inpark.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Place",
    foreignKeys = [
        ForeignKey(
            entity = Parking::class,
            parentColumns = ["id"],
            childColumns = ["parking"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ])
data class ParkingSlot(
    @PrimaryKey(autoGenerate = true)
    var placeId:Int = 0,
    var parking:Int,
    var floor:String,
    var is_reserved:Boolean,
    var number:Int
)
