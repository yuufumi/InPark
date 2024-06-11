package com.example.inpark.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.inpark.R
import com.example.inpark.data.model.Place
import com.example.inpark.outfitFamily

@Composable
fun ReservationInfoPlacePicker(items: List<Place>,selectedItem: Place, onItemSelected: (Place) -> Unit,) {
    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { expanded = !expanded }, modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp,Color(0xffA0F000)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff143C3C))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.place),
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xffA0F000),
                    contentDescription = "agenda"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Place",
                    color = Color(0xffA0F000),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = outfitFamily,
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.width(180.dp))
                Text(
                    text = "${selectedItem.floor}-${selectedItem.number}",
                    color = Color(0xffA0F000),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = outfitFamily,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset(170.dp, 0.dp)
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = "${item.floor}-${item.number}") },
                        onClick = {
                            onItemSelected(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }

}