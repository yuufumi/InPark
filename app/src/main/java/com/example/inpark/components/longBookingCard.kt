package com.example.inpark.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inpark.R
import com.example.inpark.models.Parking
import com.example.inpark.outfitFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun longBookingCard(parking: Parking,navController: NavController) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffF5FFFA)
        ),
        modifier = Modifier
            .size(width = 324.dp, height = 125.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .clickable { showBottomSheet = true },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = parking.name,
                    modifier = Modifier.padding(start = 3.dp),
                    style = TextStyle(
                        fontFamily = outfitFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xff000000)
                    )
                )
                Text(
                    text = parking.location,
                    modifier = Modifier.padding(start = 3.dp),
                    style = TextStyle(
                        fontFamily = outfitFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Color(0x5f000000)
                    )
                )
                Text(
                    text = "${parking.pricePerHour}Da/hr",
                    modifier = Modifier.padding(start = 3.dp),
                    style = TextStyle(
                        fontFamily = outfitFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = Color(0xff000000)
                    )
                )
                Row {
                    Text(
                        text = "Entry Date: ",
                        modifier = Modifier.padding(start = 3.dp),
                        style = TextStyle(
                            fontFamily = outfitFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color(0xff000000)
                        )
                    )
                    Text(
                        text = "01/05/2024 16:00",
                        modifier = Modifier.padding(start = 3.dp),
                        style = TextStyle(
                            fontFamily = outfitFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color(0xff143C3C)
                        )
                    )
                }
                Row {
                    Text(
                        text = "Entry Date: ",
                        modifier = Modifier.padding(start = 3.dp),
                        style = TextStyle(
                            fontFamily = outfitFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color(0xff000000)
                        )
                    )
                    Text(
                        text = "01/05/2024 16:00",
                        modifier = Modifier.padding(start = 3.dp),
                        style = TextStyle(
                            fontFamily = outfitFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color(0xff143C3C)
                        )
                    )
                }
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
    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = {showBottomSheet = false},contentColor = Color(0xfffffff0),sheetState = sheetState, modifier = Modifier
            .fillMaxWidth())
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally)
            {
                Row(
                    modifier = Modifier

                        .padding(horizontal = 25.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .size(width = 100.dp, height = 100.dp)
                            .background(color = Color.Transparent)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    {
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
                    Column()
                    {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Text(
                                text = parking.name,
                                modifier = Modifier.padding(start = 3.dp),
                                style = TextStyle(
                                    fontFamily = outfitFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color(0xff000000)
                                )
                            )
                            Spacer(modifier = Modifier.width(35.dp))
                            Text(
                                text = "${parking.pricePerHour}Da/hr",
                                modifier = Modifier.padding(start = 3.dp),
                                style = TextStyle(
                                    fontFamily = outfitFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    color = Color(0xff000000)
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Row(verticalAlignment = Alignment.CenterVertically)
                        {
                            Rate(5.0)
                            Spacer(modifier = Modifier.width(10.dp))
                            Divider(
                                modifier = Modifier
                                    .height(20.dp)
                                    .width(2.dp), color = Color(0xff002020)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = parking.location,
                                modifier = Modifier.padding(start = 3.dp),
                                style = TextStyle(
                                    fontFamily = outfitFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    color = Color(0x5f000000)
                                )
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = "01/05/2024 16:00",
                            style = TextStyle(
                                fontFamily = outfitFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color(0xff143C3C)
                            )
                        )
                        Divider(
                            modifier = Modifier
                                .width(50.dp)
                                .height(2.dp), color = Color(0xff002020)
                        )
                        Text(
                            text = "01/05/2024 16:00",
                            style = TextStyle(
                                fontFamily = outfitFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color(0xff143C3C)
                            )
                        )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Image(painter = painterResource(id = R.drawable.qrcode), modifier = Modifier.size(130.dp), contentDescription = "qrcode")
                Spacer(modifier = Modifier.height(15.dp))
                Button(modifier = Modifier.fillMaxWidth(0.6f),shape = RoundedCornerShape(10.dp),onClick = {navController.navigate("parkings/${parking.id}")}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xff003C3C))) {
                    Text(text="Details", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color(0xfffffff0)))
                }
                Spacer(modifier = Modifier.height(60.dp))
                }
            }
        }

    }

@Composable
fun Rate(rate: Double){
    Row(){
        Icon(imageVector = Icons.Filled.Star, modifier = Modifier.size(16.dp), tint = Color(0xff003C3C),contentDescription = "rate")
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = rate.toString(), style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xff003C3C)))
        }
}

/*

* */