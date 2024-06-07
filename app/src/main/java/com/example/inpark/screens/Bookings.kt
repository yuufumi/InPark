package com.example.inpark.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inpark.components.BookingCard
import com.example.inpark.components.InparkBottomNavBar
import com.example.inpark.components.ParkingCard
import com.example.inpark.components.SearchBar
import com.example.inpark.components.TopBar
import com.example.inpark.components.longBookingCard
import com.example.inpark.data.model.Reservation
import com.example.inpark.data.model.ReservationWithPlaceAndParking
import com.example.inpark.outfitFamily
import com.example.inpark.viewModels.LocationViewModel
import com.example.inpark.viewModels.ParkingViewModel
import com.example.inpark.viewModels.ReservationViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Bookings(navController: NavController,reservationViewModel: ReservationViewModel,locationViewModel: LocationViewModel,parkingViewModel: ParkingViewModel) {
    val ReservationsUserResponse by reservationViewModel.getReservationByUserResponse.observeAsState()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getString("id",null)
    LaunchedEffect(Unit) {
        if (userId != null) {
            reservationViewModel.getReservationByUser(userId)
        }
    }
    var reservations: List<ReservationWithPlaceAndParking>? = ReservationsUserResponse?.body()
    if(reservations != null) {
        Log.d("ALL RESERVATIONS",reservations.toString())
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xff003C3C),
        ) {
            Scaffold(
                containerColor = Color(0xff002020),
                bottomBar = { InparkBottomNavBar(navController = navController, Color(0xff002020)) }
            ) {
                Column(
                    horizontalAlignment = Alignment.Start, modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 24.dp, end = 24.dp, top = 28.dp, bottom = 60.dp)
                ) {
                    TopBar(locationViewModel)
                    Row() {
                        Text(
                            text = "Hello, ",
                            style = TextStyle(
                                fontFamily = outfitFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 36.sp,
                                color = Color(0xffFFFFF0)
                            )
                        )
                        Text(
                            text = "yuufumi",
                            style = TextStyle(
                                fontFamily = outfitFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 36.sp,
                                color = Color(0xffA0F000)
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "Your bookings",
                        style = TextStyle(
                            fontFamily = outfitFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 24.sp,
                            color = Color(0xfffffff0)
                        )
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(30.dp), modifier = Modifier
                            .verticalScroll(
                                rememberScrollState()
                            )
                            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        /*
                            reservations?.forEach { reservation ->
                            longBookingCard(reservation = reservation, navController = navController, parkingViewModel = parkingViewModel)
                        }*/
                    }

                }
            }
        }
    }else {
        CircularProgressIndicator()
    }

}