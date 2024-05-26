package com.example.inpark.data.api

import com.example.inpark.data.api.types.AuthRequest
import com.example.inpark.data.api.types.AuthResponse
import com.example.inpark.data.api.types.EmailRequest
import com.example.inpark.data.model.User
import okhttp3.OkHttpClient

import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PartMap
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface AuthApi {

    @POST(apiConstants.REGISTER)
    suspend fun registerUser(@Body user:User):Response<User>

    @POST(apiConstants.LOGIN)
    suspend fun loginUser(@Body user:AuthRequest):Response<User>

    @GET("${apiConstants.GETBYEMAIL}/{email}")
    suspend fun getByEmail(@Path("email") email:String):Response<User>

    @GET(apiConstants.GETUSERS)
    suspend fun getAllUsers():Response<List<User>>
    companion object {
        var endpoint: AuthApi? = null
        fun createEndpoint(): AuthApi {
            if(endpoint ==null) {
                val client = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30,TimeUnit.SECONDS)
                    .writeTimeout(30,TimeUnit.SECONDS)
                    .build()
                endpoint = Retrofit.Builder()
                    .client(client)
                    .baseUrl(apiConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AuthApi::class.java)
            }

        return endpoint!!
    }
    }
}