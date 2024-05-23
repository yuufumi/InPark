package com.example.inpark.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.inpark.data.model.Reservation

@Dao
interface ReservationDao {
    @Query("SELECT * FROM Reservation WHERE user=:userId")
    fun getUserReservations(userId:Int):List<Reservation>
    @Insert
    fun addReservation(reservation: Reservation)
    @Delete
    fun deleteReservation(reservation: Reservation)
}