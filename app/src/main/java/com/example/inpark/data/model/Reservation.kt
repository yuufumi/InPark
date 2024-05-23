package com.example.inpark.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Reservation",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["user"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ParkingSlot::class,
            parentColumns = ["placeId"],
            childColumns = ["place"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ])
data class Reservation(
@PrimaryKey(autoGenerate = true)
var reservationId:Int,
var user:Int,
var place:Int,
var entry_date:String,
var exit_date:String,
var entry_hour:String,
var exit_hour:String
)
