package com.example.inpark.repository

import android.util.Log
import com.example.inpark.data.api.ParkingApi
import com.example.inpark.data.api.ReservationApi
import com.example.inpark.data.dao.ParkingDao
import com.example.inpark.data.dao.ReservationDao
import com.example.inpark.data.model.Parking
import com.example.inpark.data.model.Place
import com.example.inpark.data.model.Reservation
import com.example.inpark.data.model.ReservationRequest
import com.example.inpark.data.model.ReservationResponse
import retrofit2.Response

class ReservationRepository(private val reservationApi: ReservationApi ,private val reservationDao: ReservationDao) {
    suspend fun createReservation(reservation: ReservationRequest): Response<Reservation>{
            val serverReservationresponse = reservationApi.createReservation(reservation)
            Log.d("parking reserved",serverReservationresponse.body().toString())
/*
            reservationDao.addParking(id = serverReservationresponse.body()!!.parkingId,
                nom = serverReservationresponse.body()!!.nom,
                photo = "",
                description = "",
                closing_hour = "",
                latitude = 0f,
                location = serverReservationresponse.body()!!.location,
                longitude = 0f,
                openning_hour = "",
                price_per_hour = 0f,
                rating = 5f,
                status = false,
                sat = false,
                sun = false,
                mon = false,
                tue = false,
                wed = false,
                thu = false,
                fri = false,)
            reservationDao.addParkingSlot(id = serverReservationresponse.body()!!.placeId,
                parkingId = serverReservationresponse.body()!!.parkingId,
                floor = "",
                is_reserved = true,
                number = 0)
            val hostReservation = reservationDao.createReservation(
                id = serverReservationresponse.body()!!.id,
                userId = serverReservationresponse.body()!!.userId,
                placeId = serverReservationresponse.body()!!.placeId,
                date_entree = serverReservationresponse.body()!!.date_entree,
                date_sortie = serverReservationresponse.body()!!.date_sortie,
                heure_entree = serverReservationresponse.body()!!.heure_entree,
                heure_sortie = serverReservationresponse.body()!!.heure_sortie
            )
            Log.d("HOST RESERVATION", hostReservation.toString())*/
            return serverReservationresponse
    }

    suspend fun cacheReservation(reservation: Reservation,parking: Parking,parkingSlot: Place){

        reservationDao.addParking(parking
            /*id = parking.id,
                        nom = parking.nom,
                        photo = "",
                        description = "",
                        closing_hour = "",
                        latitude = 0f,
                        location = parking.location,
                        longitude = 0f,
                        openning_hour = "",
                        price_per_hour = 0f,
                        rating = 5f,
                        status = false,
                        sat = false,
                        sun = false,
                        mon = false,
                        tue = false,
                        wed = false,
                        thu = false,
                        fri = false,

             */
        )
                    reservationDao.addParkingSlot(parkingSlot/*id = parkingSlot.id,
                        parkingId = parkingSlot.parkingId,
                        floor = "",
                        is_reserved = true,
                        number = 0*/)
                    val hostReservation = reservationDao.createReservation(reservation
                        /*id = reservation.id,
                        userId = reservation.userId,
                        placeId = reservation.placeId,
                        date_entree = reservation.date_entree,
                        date_sortie = reservation.date_sortie,
                        heure_entree = reservation.heure_entree,
                        heure_sortie = reservation.heure_sortie
                    */)
                    Log.d("HOST RESERVATION", hostReservation.toString())

    }

    suspend fun getAllReservations(): Response<List<Reservation>> {
        return reservationApi.getAllReservations()
    }

    suspend fun getReservationByUser(id:String):Response<List<ReservationResponse>> {
        return reservationApi.getReservationByUser(id)
    }

    suspend fun getReservationByUserFromCache(id:String):List<ReservationResponse> {
        return reservationDao.getAllByUser(id)
    }
}