package com.example.inpark.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inpark.R
import com.example.inpark.outfitFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mapSearchBar(navController:NavController) {
    var text by remember { mutableStateOf("") }
    Row(modifier= Modifier.fillMaxWidth().padding(horizontal = 30.dp, vertical = 50.dp),horizontalArrangement = Arrangement.spacedBy(20.dp), verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = {navController.popBackStack()},
            contentPadding = PaddingValues(12.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xffA0F000),
                containerColor = Color(0xff143C3C)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_arrow),
                modifier = Modifier.size(28.dp),
                contentDescription = "back"
            )
        }
        OutlinedTextField(value = text,onValueChange = { text = it },modifier = Modifier
            .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.search), modifier = Modifier.size(28.dp),contentDescription = "search")
            },
            placeholder = { Text(text = "Search Location", style = TextStyle(fontSize = 14.sp, fontFamily = outfitFamily, fontWeight = FontWeight.SemiBold, color = Color(0x5ffffff0)))},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedTextColor = Color(0xffffffff),
                cursorColor = Color(0xffffffff),
                containerColor = Color(0xff143C3C),
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            ),

            textStyle = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.SemiBold, fontSize = 14.sp))
    }

}