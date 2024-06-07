package com.example.inpark.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inpark.data.dao.ParkingDao
import com.example.inpark.data.dao.UserDao
import com.example.inpark.data.model.Parking
import com.example.inpark.data.model.ParkingSlot
import com.example.inpark.data.model.Reservation
import com.example.inpark.data.model.User

@Database(
    entities = [
        User::class,
        Parking::class,
        ParkingSlot::class,
        Reservation::class
    ]
    ,version = 2
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getParkingDao(): ParkingDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDBInstance(context: Context): AppDatabase {
            synchronized(this) {
                var dbInstance = INSTANCE
                if (dbInstance == null) {
                    dbInstance =
                        Room.databaseBuilder(context,AppDatabase::class.java,
                            "parking_db").build()
                    INSTANCE = dbInstance
                }
                return dbInstance
            }
        }
    }
}