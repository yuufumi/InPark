package com.example.inpark.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inpark.outfitFamily

@Composable
fun Title(value: String) {
    Text(
        text= value,
        modifier = Modifier.fillMaxWidth().heightIn(min = 40.dp),
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xffA0F000),
            fontFamily = outfitFamily

        )
    )
}