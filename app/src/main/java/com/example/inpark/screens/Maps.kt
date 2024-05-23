package com.example.inpark.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inpark.R
import com.example.inpark.components.InparkBottomNavBar
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Maps(navController: NavController) {
    var locationManager: LocationManager
    val context = LocalContext.current
    var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    var showMap by remember { mutableStateOf(false) }
    var location by remember { mutableStateOf(LatLng(36.6993,3.1755)) }
    var mapProperties by remember { mutableStateOf(MapProperties()) }
    var changeIcon by remember { mutableStateOf(false) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }
Surface(
    modifier = Modifier.fillMaxSize().background(Color(0xff002020)),
    color = Color(0xff003C3C)
) {
    Scaffold(        containerColor = Color(0xff002020),bottomBar = { InparkBottomNavBar(navController = navController,Color(0xff002020)) }) {
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
    }
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