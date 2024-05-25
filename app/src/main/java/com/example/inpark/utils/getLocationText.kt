package com.example.inpark.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import java.io.IOException
import java.util.Locale


fun getLocationText(latitude: Double, longitude: Double, context: Context): String? {
    val geocoder = Geocoder(context, Locale.getDefault())
    var addresses: List<Address>? = null

    try {
        addresses = geocoder.getFromLocation(latitude, longitude, 1)
    } catch (e: IOException) {
        e.printStackTrace()
    }

    if (addresses != null && addresses.isNotEmpty()) {
        val address = addresses[0]
        val addressText = StringBuilder()

        for (i in 0..address.maxAddressLineIndex) {
            addressText.append(address.getAddressLine(i)).append("\n")
        }

        return addressText.toString()
    }

    return null
}