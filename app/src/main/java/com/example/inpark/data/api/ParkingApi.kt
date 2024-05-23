package com.example.inpark.data.api

import com.example.inpark.data.model.Parking
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


data class SearchRequest(
    val query: String
)
interface ParkingApi {
    @GET(apiConstants.GETALLPARKINGS)
    suspend fun getAllParkings(): Response<List<Parking>>

    @GET("${apiConstants.GETALLPARKINGS}/{id}")
    suspend fun getParkingById(@Path("id") id: String): Response<Parking>

    @GET(apiConstants.SEARCHPARKINGS)
    suspend fun searchParkings(@Body query: SearchRequest): Response<List<Parking>>

    companion object {
        var endpoint: ParkingApi? = null
        fun createEndpoint(): ParkingApi {
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
                    .create(ParkingApi::class.java)
            }

            return endpoint!!
        }
    }
}