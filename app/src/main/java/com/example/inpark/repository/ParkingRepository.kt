package com.example.inpark.repository

import com.example.inpark.data.api.ParkingApi
import com.example.inpark.data.api.SearchRequest
import com.example.inpark.data.model.Parking
import com.example.inpark.data.model.Place
import retrofit2.Response

class ParkingRepository(private val parkingApi: ParkingApi) {
    suspend fun getAllParkings(): Response<List<Parking>> {
        return parkingApi.getAllParkings()
    }

    suspend fun getParkingById(id : String): Response<Parking> {
        return  parkingApi.getParkingById(id)
    }

    suspend fun searchParkings(query: SearchRequest): Response<List<Parking>> {
        return parkingApi.searchParkings(query)
    }

    suspend fun getSlotsByParking(id: String): Response<List<Place>>{
        return parkingApi.getSlotsByParking(id)
    }

    suspend fun getParkingByPlaceId(id:String): Response<Parking>{
        return parkingApi.getParkingByPlaceId(id)
    }
}