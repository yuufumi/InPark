package com.example.inpark.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.inpark.data.model.Parking
import com.example.inpark.data.model.Place
import com.example.inpark.data.model.Reservation
import com.example.inpark.data.model.ReservationRequest
import com.example.inpark.data.model.ReservationResponse
import com.example.inpark.data.model.User

@Dao
interface ReservationDao {
    @Query("SELECT r.id,r.'userId', r.date_entree, r.date_sortie, r.heure_entree,r.heure_sortie,r.placeId,pl.'parkingId',p.nom,p.location,p.photo,p.price_per_hour,p.rating FROM 'Reservation' r INNER JOIN 'Place' pl ON r.'placeId' = pl.id INNER JOIN 'Parking' p ON pl.'parkingId' = p.id INNER JOIN 'User' u ON u.id=r.'userId' WHERE u.id=:id;")
    fun getAllByUser(id:String):List<ReservationResponse>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addParkingSlot(parkingSlot: Place)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addParking(parking:Parking)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createReservation(reservation:Reservation
    )
}