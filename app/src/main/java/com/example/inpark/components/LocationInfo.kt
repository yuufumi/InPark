package com.example.inpark.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inpark.R
import com.example.inpark.outfitFamily

@Composable
fun LocationInfo(){
Row{
    Image(painter = painterResource(id = R.drawable.unselected_maps), modifier = Modifier.size(30.dp), contentDescription = "location")
    Column {
        Text(text="Location", style = TextStyle(fontFamily = outfitFamily, color = Color(0x7fFFFFf0), fontSize = 12.sp))
        Text(text="Oued Smar, Algiers, Algeria", style = TextStyle(fontFamily = outfitFamily, color = Color(0xffFFFFF0), fontSize = 12.sp))
    }
}
}