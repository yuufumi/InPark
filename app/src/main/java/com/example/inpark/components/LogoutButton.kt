package com.example.inpark.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.inpark.R
import com.example.inpark.outfitFamily

@Composable
fun LogoutButton(onClick: () -> Unit){
    Button(onClick = onClick,modifier = Modifier
        .fillMaxWidth(0.7f)
        .height(40.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xffD53939)) ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Image(modifier = Modifier.size(22.dp), painter = painterResource(id = R.drawable.logout), contentDescription = "google logo")
            Spacer(modifier = Modifier.width(50.dp))
            Text(
                text = "Logout",
                color = Color(0xfffffff0),
                style = TextStyle(fontSize = 18.sp, fontFamily = outfitFamily, fontWeight = FontWeight.SemiBold)
            )
        }
    }
}