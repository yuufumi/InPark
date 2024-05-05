package com.example.inpark.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.inpark.R

@Composable
fun Notifications() {
    Button(onClick = {},  colors = ButtonDefaults.buttonColors(containerColor = Color(0xff143C3C))) {
        Image(painter = painterResource(id = R.drawable.bell), modifier = Modifier.size(22.dp), contentDescription = "bell")
    }
}