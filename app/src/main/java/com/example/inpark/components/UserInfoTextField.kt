package com.example.inpark.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
fun UserInfoTextField(label:String,placeholder:String,type:String,size:Float=1f){
    var text by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text=label, style = TextStyle(fontFamily = outfitFamily, color = Color.White, fontWeight = FontWeight.Normal, fontSize = 18.sp))
        if(type=="password"){
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth(size)
                    .height(50.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = @androidx.compose.runtime.Composable {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        if (!passwordVisible){
                            Image(modifier = Modifier.size(22.dp), painter = painterResource(id = R.drawable.mdi_eye), contentDescription = "show")
                        } else {
                            Image(modifier = Modifier.size(22.dp), painter = painterResource(id = R.drawable.mdi_hide),contentDescription = "hide")
                        }
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color(0xffffffff),
                    cursorColor = Color(0xffffffff),
                    containerColor = Color(0xff143C3C),
                    focusedBorderColor = Color(0xffA0F000)
                ),

                textStyle = TextStyle(fontFamily = outfitFamily, fontSize = 18.sp)
            )
        }else{
            OutlinedTextField(value = text,onValueChange = { text = it },modifier = Modifier
                .fillMaxWidth(size)
                .height(50.dp),
                placeholder = {Text(text = placeholder, style = TextStyle(fontSize = 12.sp, fontFamily = outfitFamily, color = Color(0x55fffff0)))},
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color(0xffffffff),
                    cursorColor = Color(0xffffffff),
                    containerColor = Color(0xff143C3C),
                    focusedBorderColor = Color(0xffA0F000)
                ),

                textStyle = TextStyle(fontFamily = outfitFamily, fontSize = 18.sp)
            )

        }

    }
}

fun handleChange(){}