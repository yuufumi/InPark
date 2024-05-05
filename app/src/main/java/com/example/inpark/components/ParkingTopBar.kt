package com.example.inpark.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inpark.R
import com.example.inpark.outfitFamily

@Composable
fun ParkingTopBar(navController: NavController){
    Row(horizontalArrangement = Arrangement.spacedBy(40.dp), verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = {navController.popBackStack()},
            contentPadding = PaddingValues(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xffA0F000),
                containerColor = Color(0xff143C3C)
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_arrow),
                modifier = Modifier.size(28.dp),
                contentDescription = "back"
            )
        }
        Text(text = "parking Details", style= TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 24.sp, color = Color(0xfffffff0)))
        Button(
            onClick = {},
            contentPadding = PaddingValues(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xffA0F000),
                containerColor = Color(0xff143C3C)
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.save_parking),
                modifier = Modifier.size(28.dp),
                contentDescription = "back"
            )
        }
    }

}