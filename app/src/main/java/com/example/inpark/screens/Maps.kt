package com.example.inpark.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import com.example.inpark.R
import com.example.inpark.components.TopBar
import com.example.inpark.components.mapSearchBar
import com.example.inpark.databinding.ActivityMapsBinding
import com.example.inpark.utils.AppPermissions
import com.example.inpark.utils.getCurrentLocation
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun Maps(context: Context, navController: NavController) {
    var locationManager: LocationManager

    var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    var showMap by remember { mutableStateOf(false) }
    var location by remember { mutableStateOf(LatLng(36.6993,3.1755)) }
    var mapProperties by remember { mutableStateOf(MapProperties()) }
    var changeIcon by remember { mutableStateOf(false) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }

    getCurrentLocation(context) {
        location = it
        showMap = true
    }
    if (showMap) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        )}
    else {
        Text(text = "Loading Map...")
    }
    mapSearchBar(navController)
}
/*
fun getLocation(context: Context) {
    locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    if ((ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
        ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 2)
    }
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, context)
}


*/