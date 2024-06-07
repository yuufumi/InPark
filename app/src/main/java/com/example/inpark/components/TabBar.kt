package com.example.inpark.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
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
fun TabBar(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) ->Unit
){
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color(0xfffffff0),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
            )
        }
    ) {
            Tab(
                selected = 0 == selectedTabIndex,
                onClick = { onTabSelected(0) },
                text = { Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.save_parking), modifier = Modifier.size(22.dp), contentDescription = "saved", tint = Color(0xfffffff0))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Saved Parkings", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp, color = Color(0xfffffff0)))
                } }
            )
            Tab(
                selected = 1 == selectedTabIndex,
                onClick = { onTabSelected(1) },
                text = { Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Info, modifier = Modifier.size(22.dp), contentDescription = "info", tint = Color(0xfffffff0))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "UserInfo", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp, color = Color(0xfffffff0)))
                }  }
            )
        }
    }