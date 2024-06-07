package com.example.inpark.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Reservation",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ParkingSlot::class,
            parentColumns = ["id"],
            childColumns = ["placeId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ])
data class Reservation(
@PrimaryKey(autoGenerate = false)
var id:Int,
var userId:Int,
var placeId:Int,
var date_entree:String,
var date_sortie:String,
var heure_entree:String,
var heure_sortie:String
)

data class ReservationRequest(
    var userId:Int,
    var placeId:Int,
    var date_entree:String,
    var date_sortie:String,
    var heure_entree:String,
    var heure_sortie:String
)

data class ReservationWithPlaceAndParking(
    @Embedded val reservation: Reservation,
    @Relation(
        parentColumn = "placeId",
        entityColumn = "id"
    )
    val place: PlaceWithParking
)

data class PlaceWithParking(
    @Embedded val place: ParkingSlot,
    @Relation(
        parentColumn = "parkingId",
        entityColumn = "id"
    )
    val parking: Parking
)

