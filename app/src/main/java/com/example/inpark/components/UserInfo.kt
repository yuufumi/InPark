package com.example.inpark.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
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
fun UserInfo(label: String,value: String, size: Float=1f){

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text=label, style = TextStyle(fontFamily = outfitFamily, color = Color(0xfffffff0), fontWeight = FontWeight.Normal, fontSize = 18.sp))
        OutlinedTextField(
                value = value,
                onValueChange = {},
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth(size)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = Color(0xfffffff0),
                    containerColor = Color(0xff143C3C)
                ),

                textStyle = TextStyle(fontFamily = outfitFamily, fontSize = 16.sp, color = Color(0xfffffff0))
            )
    }
}