package com.example.inpark.components

import android.content.SharedPreferences
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inpark.outfitFamily

@Composable
fun CustomButton(label:String, color: String="", onbtnClick: () -> Unit = {}){
    Button(onClick = onbtnClick,modifier = Modifier.fillMaxWidth().height(40.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xffa0f000)) ) {
        Text(
            text = label,
            color = Color(0xff003C3C),
            style = TextStyle(fontSize = 18.sp, fontFamily = outfitFamily, fontWeight = FontWeight.Bold)
        )
    }    
}