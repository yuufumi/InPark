package com.example.inpark.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inpark.R
import com.example.inpark.outfitFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(value = text,onValueChange = { text = it },modifier = Modifier
        .fillMaxWidth(),
    shape = RoundedCornerShape(30.dp),
        leadingIcon = {
                       Icon(painter = painterResource(id = R.drawable.search), modifier = Modifier.size(28.dp),contentDescription = "search")
        },
        placeholder = { Text(text = "Find Parking", style = TextStyle(fontSize = 16.sp, fontFamily = outfitFamily, color = Color(0xfffffff0)))},
    colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedTextColor = Color(0xffffffff),
        cursorColor = Color(0xffffffff),
        containerColor = Color(0xff143C3C),
        unfocusedBorderColor = Color.Transparent,
        focusedBorderColor = Color.Transparent
    ),

    textStyle = TextStyle(fontFamily = outfitFamily, fontSize = 18.sp))
}

