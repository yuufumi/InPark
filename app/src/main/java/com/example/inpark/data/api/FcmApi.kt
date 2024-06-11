package com.example.inpark.data.api

import com.example.inpark.data.dto.SendMessageDto
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface FcmApi {

    @POST("/send")
    suspend fun sendMessage(@Body body: SendMessageDto)

    @POST("/broadcast")
    suspend fun broadcast(@Body body: SendMessageDto)

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