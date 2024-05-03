package com.example.inpark.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inpark.components.CustomButton
import com.example.inpark.components.Title
import com.example.inpark.components.UserInfoTextField
import com.example.inpark.outfitFamily

@Composable
fun Signup(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 30.dp, end = 30.dp, top = 40.dp, bottom = 40.dp)
        .verticalScroll(
            rememberScrollState()
        ),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Title(value = "Sign up")
        UserInfoTextField(label = "Username", placeholder = "example: yuufumi", type = "")
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(30.dp),
        ){
            UserInfoTextField(label = "First name", placeholder = "", type = "",size=0.5f)
            UserInfoTextField(label = "Last name", placeholder = "", type = "",size=1f)
        }
        UserInfoTextField(label = "Email Address", placeholder = "example@gmail.com", type = "")
        UserInfoTextField(label = "Phone number", placeholder = "0666063622", type = "")
        UserInfoTextField(label = "Password", placeholder = "", type = "password")
        UserInfoTextField(label = "Confirm password", placeholder = "", type = "password")
        CustomButton(label = "Register")
        Row {
            Text(
                text = "Do you have an account?",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color(0xfffffff0),
                    fontSize = 14.sp,
                    fontFamily = outfitFamily,
                )
            )
            Text(
                modifier = Modifier.clickable(onClick = {navController.navigate("signin")}),
                text = " Sign In",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color(0xFFA0F000),
                    fontSize = 14.sp,
                    fontFamily = outfitFamily,
                    textDecoration = TextDecoration.Underline
                )
            )
        }

    }
}