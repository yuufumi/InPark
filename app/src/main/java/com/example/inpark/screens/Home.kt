package com.example.inpark.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.material.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inpark.components.BookingCard
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.example.inpark.components.SearchBar
import com.example.inpark.components.BookingCard
import com.example.inpark.components.InparkBottomNavBar
import com.example.inpark.components.ParkingCard
import com.example.inpark.components.Title
import com.example.inpark.components.TopBar
import com.example.inpark.data.model.Parking
import com.example.inpark.outfitFamily
import com.example.inpark.viewModels.LocationViewModel
import com.example.inpark.viewModels.ParkingViewModel
import java.nio.file.WatchEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController,parkingViewModel: ParkingViewModel,locationViewModel: LocationViewModel) {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val allParkingsResponse by parkingViewModel.allParkingsResponse.observeAsState()
    LaunchedEffect(Unit) {
        parkingViewModel.getAllParkings()
    }
    var parkings: List<Parking>? = allParkingsResponse?.body()
    Scaffold(
        bottomBar = { InparkBottomNavBar(navController = navController,Color(0xff002020)) },
        containerColor = Color(0xff002020)    ){
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 28.dp)
            .verticalScroll(rememberScrollState())
        ){
            TopBar(locationViewModel)
            val username = sharedPreferences.getString("username",null)
            Row(){
                Text(text = "Hello, ", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 36.sp, color = Color(0xffFFFFF0)))
                Text(text = username!!, style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 36.sp, color = Color(0xffA0F000)))
            }
            Spacer(modifier = Modifier.height(40.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Recent bookings", style= TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 24.sp, color = Color(0xfffffff0)))
            Spacer(modifier = Modifier.height(30.dp))
            if(parkings == null){
                CircularProgressIndicator()
            }else {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(26.dp)) {
                    items(parkings!!.size) { index ->
                        BookingCard(parkings[index])
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Available parkings", style= TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 24.sp, color = Color(0xfffffff0)))
            Spacer(modifier = Modifier.height(30.dp))
            if(parkings == null){
                CircularProgressIndicator()
            }else {
                Column(modifier = Modifier
                    .fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    parkings!!.forEach {
                            parking ->
                        ParkingCard(parking = parking, navController = navController)
                    }
                }
            }
            Spacer(modifier = Modifier.height(100.dp))

        }
    }

}

