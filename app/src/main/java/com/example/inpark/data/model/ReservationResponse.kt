package com.example.inpark.data.model

data class ReservationResponse(
    var id:Int,
    var userId:Int,
    var date_entree:String,
    var date_sortie:String,
    var heure_entree:String,
    var heure_sortie:String,
    var placeId: Int,
    var parkingId: Int,
    var nom:String,
    var location:String,
    var photo: String?,
    var price_per_hour:Float,
    var rating: Float,
)
