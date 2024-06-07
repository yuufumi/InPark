package com.example.inpark.data.api

import com.example.inpark.data.model.Parking
import com.example.inpark.data.model.Reservation
import com.example.inpark.data.model.ReservationRequest
import com.example.inpark.data.model.ReservationWithPlaceAndParking
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ReservationApi {
    @GET(apiConstants.ALLRESERVATIONS)
    suspend fun getAllReservations(): Response<List<Reservation>>

    @GET("${apiConstants.RESERVATIONSBYUSER}/{id}")
    suspend fun getReservationByUser(@Path("id") id: String): Response<List<ReservationWithPlaceAndParking>>

    @POST(apiConstants.CREATERESERVATION)
    suspend fun createReservation(@Body reservation: ReservationRequest): Response<Reservation>

    companion object {
        var endpoint: ReservationApi? = null
        fun createEndpoint(): ReservationApi {
            if (endpoint == null) {
                val client = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build()
                endpoint = Retrofit.Builder()
                    .client(client)
                    .baseUrl(apiConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ReservationApi::class.java)
            }

            return endpoint!!
        }
    }
}