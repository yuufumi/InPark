package com.example.inpark.screens

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inpark.components.BookingCard
import com.example.inpark.components.ParkingCard
import com.example.inpark.components.SearchBar
import com.example.inpark.components.TopBar
import com.example.inpark.components.longBookingCard
import com.example.inpark.models.Parking
import com.example.inpark.outfitFamily

@Composable
fun Bookings(navController: NavController) {
    val cardData = listOf(
        Parking(1, "Lot A", "Large parking lot near main entrance Large parking lot near main entrance Large parking lot near main entrance Large parking lot near main entrance Large parking lot near main entrance Large parking lot near main entrance Large parking lot near main entrance", 3.00, "Mall", true),
        Parking(2, "Garage B", "Multi-level parking garage Multi-level parking garage Multi-level parking garage Multi-level parking garage Multi-level parking garage Multi-level parking garage", 4.00, "Downtown", false),
        Parking(3, "Street Parking", "Metered street parking on Elm Street", 2.00, "City Center", true),
        Parking(4, "Valet Parking", "Convenient valet service at the restaurant", 10.00, "Restaurant District", true),
        Parking(5, "Employee Lot", "Reserved parking for employees only", 0.00, "Company HQ", false),
        Parking(6, "Motorcycle Parking", "Designated motorcycle parking area", 1.50, "Stadium", true),
        Parking(7, "Permit Parking", "Requires a residential parking permit", 0.00, "Neighborhood", true),
        Parking(8, "Short-Term Parking", "Limited to 30 minutes", 5.00, "Airport", true),
        Parking(9, "Overnight Parking", "Permitted for overnight stays", 8.00, "Hotel", true),
        Parking(10, "Handicap Parking", "Accessible parking spaces", 0.00, "Hospital", true)
    )
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier
        .fillMaxSize()
        .padding(start = 24.dp, end = 24.dp, top = 28.dp, bottom = 60.dp)
        ){
            TopBar()
            Row(){
                Text(text = "Hello, ", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 36.sp, color = Color(0xffFFFFF0)))
                Text(text = "yuufumi", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 36.sp, color = Color(0xffA0F000)))
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Your bookings", style= TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 24.sp, color = Color(0xfffffff0)))
            Spacer(modifier = Modifier.height(30.dp))
            Column(verticalArrangement = Arrangement.spacedBy(30.dp),modifier = Modifier
                .verticalScroll(
                    rememberScrollState()
                )
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                cardData.forEach {
                    card ->
                    longBookingCard(parking = card,navController = navController)
                }
        }

    }
}