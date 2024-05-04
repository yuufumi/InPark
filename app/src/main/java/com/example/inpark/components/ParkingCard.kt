package com.example.inpark.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inpark.R
import com.example.inpark.models.Parking
import com.example.inpark.outfitFamily

@Composable
fun ParkingCard(parking: Parking) {
    Card (
    colors = CardDefaults.cardColors(
        containerColor = Color(0xffF5FFFA)
    ),
    modifier = Modifier
        .size(width=318.dp, height = 125.dp)

    ){
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(text = parking.name,modifier = Modifier.padding(start = 3.dp), style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xff000000)))
            Text(text = parking.location, modifier = Modifier.padding(start = 3.dp),style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp, color = Color(0x5f000000)))
            Spacer(modifier = Modifier.height(10.dp))
            if(parking.isOpen){
            Button(onClick = {},     contentPadding = PaddingValues(), modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp) ,enabled = false, colors = ButtonDefaults.buttonColors(disabledContainerColor = Color(0xffD1E7DD), disabledContentColor = Color(0xff0A3622))) {
                Text(text = "Opened",style = TextStyle(fontFamily = outfitFamily, fontSize = 12.sp))
            }
            }else {
                Button(onClick = {}, contentPadding = PaddingValues(), modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp) , enabled = false, colors = ButtonDefaults.buttonColors(disabledContainerColor = Color(0xffF8D7DA), disabledContentColor = Color(0xff58151C))) {
                    Text(text = "Closed",style = TextStyle(fontFamily = outfitFamily, fontSize = 12.sp))
                }
            }
            //Text(text = "${parking.pricePerHour}Da/hr", modifier = Modifier.padding(start = 3.dp),style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.SemiBold, fontSize = 12.sp, color = Color(0xff000000)))

        }
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 100.dp) // Fills the entire screen
                .background(color = Color.Transparent)
                .clip(RoundedCornerShape(10.dp)),
            // Optional background color (useful for visibility)
        ) {
            // Add your content here (e.g., Text, Column, etc.)
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,
                // Fills the container (adjust as// Resize behavior
                painter = painterResource(id = R.drawable.parking_example), // Replace with your image resource
                contentDescription = "parking Image" // Optional content description for accessibility
            )
        }
    }
}
}