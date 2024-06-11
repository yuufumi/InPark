package com.example.inpark.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.inpark.components.DetailsStickyBottomBar
import com.example.inpark.data.model.Parking
import com.example.inpark.viewModels.ParkingViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun parkingDetailsScreen(parkingViewModel: ParkingViewModel, parkingId: String, navController: NavController){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val parkingResponse by parkingViewModel.parkingByIdResponse.observeAsState()
    LaunchedEffect(Unit) {
        parkingViewModel.getParkingById(parkingId)
    }
    var parking: Parking? = parkingResponse?.body()
    if(parking != null){
    Scaffold(bottomBar = {
        DetailsStickyBottomBar(parking.price_per_hour.toInt()) { navController.navigate("reservations/${parkingId}") }
    },containerColor = Color(0xff002020)){
        parkingDetails(parkingViewModel = parkingViewModel, parkingId = parkingId, navController = navController)
    }

    }else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xffA0F000))
        }
    }}