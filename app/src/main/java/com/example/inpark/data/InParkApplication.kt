package com.example.inpark.data

import android.app.Application
import android.content.Context
import com.example.inpark.data.database.AppDatabase
import com.example.inpark.repository.UserRepository

class InParkApplication(
    private val context: Context
) : Application() {
    private val database by lazy {
        AppDatabase.getDBInstance(context)
    }
    // Data Access Objects (DAOs)
    private val userDao by lazy {
        this.database.getUserDao()
    }
    private val parkingDao by lazy {
        this.database.getParkingDao()
    }
    private val reservationDao by lazy {
        this.database.getReservationDao()
    }

    // Repositories
    val userRepository by lazy {
        UserRepository(this.userDao)
    }
}