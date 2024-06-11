package com.example.inpark.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.inpark.R
import com.example.inpark.data.model.Parking
import com.example.inpark.outfitFamily
import com.example.inpark.utils.checkHourStatus
import com.example.inpark.utils.extractHour

@Composable
fun MainParkingDetails(parking:Parking){
    Column{
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp) // Fills the entire screen
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
                painter = rememberAsyncImagePainter(parking.photo), // Replace with your image resource
                contentDescription = "parking Image" // Optional content description for accessibility
            )
        }
        Spacer(modifier = Modifier.height(34.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = parking.nom, style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color(0xfffffff0)))
            if(checkHourStatus(parking.openning_hour, parking.closing_hour)){
                Button(onClick = {},enabled = false, shape = RoundedCornerShape(30),colors = ButtonDefaults.buttonColors(disabledContainerColor = Color(0xffD1E7DD), disabledContentColor = Color(0xff0A3622))) {
                    Text(text = "Opened",style = TextStyle(fontFamily = outfitFamily, fontSize = 18.sp))
                }
            }else {
                Button(onClick = {}, enabled = false, shape = RoundedCornerShape(30), colors = ButtonDefaults.buttonColors(disabledContainerColor = Color(0xffF8D7DA), disabledContentColor = Color(0xff58151C))) {
                    Text(text = "Closed",style = TextStyle(fontFamily = outfitFamily, fontSize = 18.sp))
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(verticalAlignment = Alignment.Top) {
            Icon(painter = painterResource(id = R.drawable.selected_maps), modifier = Modifier.size(26.dp), contentDescription = "location", tint = Color(0x9fA0F000))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = parking.location, style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 22.sp, color = Color(0x9fA0F000)))
        }
        Spacer(modifier = Modifier.height(15.dp))
        Divider(
            modifier = Modifier
                .height(1.dp) // Adjust height as needed
                .fillMaxWidth(1f),
            color = Color(0x5ffffff0)
        )
        Spacer(modifier = Modifier.height(15.dp))
        val rate: Double = 5.0
        Row (verticalAlignment = Alignment.CenterVertically){
            Rating(rate)
            Spacer(modifier = Modifier.width(10.dp))
            Divider(modifier = Modifier
                .height(24.dp)
                .width(2.dp),color = Color(0x5ffffff0))
            Spacer(modifier = Modifier.width(10.dp))
            freeSpaces(free = 10)
            Spacer(modifier = Modifier.width(10.dp))
            Divider(modifier = Modifier
                .height(24.dp)
                .width(2.dp),color = Color(0x5ffffff0))
            Spacer(modifier = Modifier.width(10.dp))
            Pricing(price = parking.price_per_hour.toString())
        }
        Spacer(modifier = Modifier.height(15.dp))
        Divider(
            modifier = Modifier
                .height(1.dp) // Adjust height as needed
                .fillMaxWidth(1f),
            color = Color(0x5ffffff0)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            Text(text = "Open from ",style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Normal, fontSize = 22.sp, color = Color(0x9ffffff0)))
            Text(text = "${extractHour(parking.openning_hour)}h",style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Normal, fontSize = 22.sp, color = Color(0xffA0F000)))
            Text(text = " to ", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Normal, fontSize = 22.sp, color = Color(0x9ffffff0)))
            Text(text = "${extractHour(parking.closing_hour)}h",style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Normal, fontSize = 22.sp, color = Color(0xffA0F000)))
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)), horizontalArrangement = Arrangement.SpaceBetween){
            day(day = "Sun", open = parking.sun)
            day(day = "Mon", open = parking.mon)
            day(day = "Tue", open = parking.tue)
            day(day = "Wed", open = parking.wed)
            day(day = "Thu", open = parking.thu)
            day(day = "Fri", open = parking.fri)
            day(day = "Sat", open = parking.sat)
        }

    }
}

@Composable 
fun Rating(rate: Double){
    Row(verticalAlignment = Alignment.CenterVertically,){
        Text(text = rate.toString(), style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xffA0F000)))
        Icon(imageVector = Icons.Filled.Star, modifier = Modifier.size(16.dp), tint = Color(0xffA0F000),contentDescription = "rate")
        Spacer(modifier = Modifier.width(10.dp))
        Icon(imageVector = Icons.Filled.Star, modifier = Modifier.size(16.dp), tint = Color(0xffA0F000),contentDescription = "rate")
        Icon(imageVector = Icons.Filled.Star, modifier = Modifier.size(16.dp), tint = Color(0xffA0F000),contentDescription = "rate")
        Icon(imageVector = Icons.Filled.Star, modifier = Modifier.size(16.dp), tint = Color(0xffA0F000),contentDescription = "rate")
        Icon(imageVector = Icons.Filled.Star, modifier = Modifier.size(16.dp), tint = Color(0xffA0F000),contentDescription = "rate")
    }
}

@Composable
fun freeSpaces(free: Int){
    Row(verticalAlignment = Alignment.CenterVertically){
        Icon(painter = painterResource(id = R.drawable.free_space), modifier = Modifier.size(20.dp), tint = Color(0x5ffffff0),contentDescription = "space")
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "${free.toString()} free spaces", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 12.sp, color = Color(0x5ffffff0)))
    }
}

@Composable
fun Pricing(price:String){
    Row(verticalAlignment = Alignment.CenterVertically){
        Icon(painter = painterResource(id = R.drawable.money), modifier = Modifier.size(20.dp), tint = Color(0x5ffffff0),contentDescription = "space")
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "${price}DA/hr", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 12.sp, color = Color(0x5ffffff0)))
    }

}

@Composable
fun day(day:String,open:Boolean){
    if(open){
    Box(modifier = Modifier
        .background(
            color = Color.Transparent
        )
        .padding(horizontal = 10.dp, vertical = 10.dp)){
        Text(text = day,style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color(0xfffffff0)))
    }
    }else{
        Box(modifier = Modifier
            .background(
                color = Color(0xffA0F000)
            )
            .padding(horizontal = 10.dp, vertical = 10.dp)){
            Text(text = day, textDecoration = TextDecoration.LineThrough,style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color(0xff002020)))
        }
    }
}