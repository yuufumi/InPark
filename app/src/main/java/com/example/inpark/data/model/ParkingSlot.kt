package com.example.inpark.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Place",
    foreignKeys = [
        ForeignKey(
            entity = Parking::class,
            parentColumns = ["id"],
            childColumns = ["parkingId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ])
data class ParkingSlot(
    @PrimaryKey(autoGenerate = false)
    var id:Int,
    var parkingId:Int,
    var floor:String,
    var is_reserved:Boolean,
    var number:Int
)
