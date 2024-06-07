package com.example.inpark.repository

import com.example.inpark.data.api.ParkingApi
import com.example.inpark.data.api.ReservationApi
import com.example.inpark.data.model.Reservation
import com.example.inpark.data.model.ReservationRequest
import com.example.inpark.data.model.ReservationWithPlaceAndParking
import retrofit2.Response

class ReservationRepository(private val reservationApi: ReservationApi) {
    suspend fun createReservation(reservation: ReservationRequest): Response<Reservation> {
        return reservationApi.createReservation(reservation)
    }

    suspend fun getAllReservations(): Response<List<Reservation>> {
        return reservationApi.getAllReservations()
    }

    suspend fun getReservationByUser(id:String):Response<List<ReservationWithPlaceAndParking>> {
        return reservationApi.getReservationByUser(id)
    }
}