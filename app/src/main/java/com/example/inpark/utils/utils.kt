package com.example.inpark.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.SimpleTimeZone
import java.util.TimeZone


fun extractHour(timeStamp: String): String{
    val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
    val zonedDateTime = ZonedDateTime.parse(timeStamp,formatter)
    return zonedDateTime.hour.toString()
}

fun checkHourStatus(openingHour: String, closingHour: String): Boolean{
    val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME

    val openingZonedDateTime = ZonedDateTime.parse(openingHour, formatter).withZoneSameInstant(ZoneId.systemDefault())
    val closingZonedDateTime = ZonedDateTime.parse(closingHour, formatter).withZoneSameInstant(ZoneId.systemDefault())

    val openingLocalTime = openingZonedDateTime.toLocalTime()
    val closingLocalTime = closingZonedDateTime.toLocalTime()

    val currentLocalTime = LocalTime.now(ZoneId.systemDefault())

    return if (openingLocalTime.isBefore(closingLocalTime)) {
        currentLocalTime.isAfter(openingLocalTime) && currentLocalTime.isBefore(closingLocalTime)
    } else {
        // Handle overnight business hours (e.g., 10 PM to 6 AM)
        currentLocalTime.isAfter(openingLocalTime) || currentLocalTime.isBefore(closingLocalTime)
    }
}
fun checkForPermission(context: Context): Boolean {
    return !(ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
}

@SuppressLint("MissingPermission")
fun getCurrentLocation(context: Context, onLocationFetched: (location: LatLng) -> Unit) {
    var loc: LatLng
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: Location? ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude
                loc = LatLng(latitude,longitude)
                onLocationFetched(loc)
            }
        }
        .addOnFailureListener { exception: Exception ->
            // Handle failure to get location
            Log.d("MAP-EXCEPTION",exception.message.toString())
        }

}

fun convertToISODateTime(dateString: String): String {
    val inputFormat = SimpleDateFormat("dd/M/yyyy", java.util.Locale.getDefault())
    val date = inputFormat.parse(dateString)
    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", java.util.Locale.getDefault())
    outputFormat.timeZone = TimeZone.getTimeZone("UTC")
    return outputFormat.format(date)
}

fun convertToDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", java.util.Locale.getDefault())
    val date = inputFormat.parse(dateString)
    val outputFormat = SimpleDateFormat("dd/M/yyyy", java.util.Locale.getDefault())
    outputFormat.timeZone = TimeZone.getTimeZone("UTC")
    return outputFormat.format(date)
}


fun isWifiEnabled(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    } else {
        @Suppress("DEPRECATION")
        val networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        @Suppress("DEPRECATION")
        return networkInfo?.isConnected == true
    }
}

fun isMobileDataEnabled(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    } else {
        @Suppress("DEPRECATION")
        val networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        @Suppress("DEPRECATION")
        return networkInfo?.isConnected == true
    }
}

fun reformatTime(time: String): String {
    val parts = time.split(":")
    val hour = "%02d".format(parts[0].toInt())
    val minute = "%02d".format(parts[1].toInt())
    return "$hour:$minute"
}
