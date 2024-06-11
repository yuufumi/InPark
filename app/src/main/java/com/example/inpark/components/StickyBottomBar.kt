package com.example.inpark.components

import android.telecom.Call.Details
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inpark.outfitFamily


@Composable
fun StickyBottomBar(
    price: Int,
    onButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xff002020))
            .padding(horizontal = 30.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${price} DA",
                style = TextStyle(
                    color = Color(0xffA0F000),
                    fontSize = 18.sp,
                    fontFamily = outfitFamily,
                    fontWeight = FontWeight.Bold)
            )
            Button(
                onClick = onButtonClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xffA0F000)
                )
            ) {
                Text(
                    text = "Confirm Booking",
                    style = TextStyle(
                        color = Color(0xff002020),
                        fontSize = 14.sp,
                        fontFamily = outfitFamily,
                        fontWeight = FontWeight.Bold)

                )
            }
        }
    }
}

@Composable
fun DetailsStickyBottomBar(
    price: Int,
    onButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xff002020))
            .padding(horizontal = 30.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${price} DA/Hr",
                style = TextStyle(
                    color = Color(0xffA0F000),
                    fontSize = 18.sp,
                    fontFamily = outfitFamily,
                    fontWeight = FontWeight.Bold)
            )
            Button(
                onClick = onButtonClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xffA0F000)
                )
            ) {
                Text(
                    text = "Reserve Now",
                    style = TextStyle(
                        color = Color(0xff002020),
                        fontSize = 14.sp,
                        fontFamily = outfitFamily,
                        fontWeight = FontWeight.Bold)

                )
            }
        }
    }
}