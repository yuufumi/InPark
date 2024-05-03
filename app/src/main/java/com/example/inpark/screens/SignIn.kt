package com.example.inpark.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inpark.components.CustomButton
import com.example.inpark.components.GoogleButton
import com.example.inpark.components.Title
import com.example.inpark.components.UserInfoTextField
import com.example.inpark.outfitFamily

@Composable
fun SignIn (navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp, top = 120.dp)
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Title(value = "Login")
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserInfoTextField(label = "Username", placeholder = "example: yuufumi", type = "")
            UserInfoTextField(label = "Password", placeholder = "", type = "password")
            CustomButton(label = "Login")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Divider(
                    modifier = Modifier
                        .height(1.dp) // Adjust height as needed
                        .fillMaxWidth(0.46f)
                )

                Text(
                    text = "or",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 5.dp),
                    style = TextStyle(
                        fontFamily = outfitFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xfffffff0),
                        fontSize = 16.sp
                    )
                )
                Divider(
                    modifier = Modifier
                        .height(1.dp) // Adjust height as needed
                        .fillMaxWidth(1f)
                )


            }
            GoogleButton()
            Row {
                Text(
                    text = "You don’t have an account?",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color(0xfffffff0),
                        fontSize = 14.sp,
                        fontFamily = outfitFamily,
                    )
                )
                Text(
                    modifier = Modifier.clickable(onClick = {navController.navigate("signup")}),
                        text = " Sign up",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color(0xFFA0F000),
                            fontSize = 14.sp,
                            fontFamily = outfitFamily,
                            textDecoration = TextDecoration.Underline
                        ),
                    )



            }

        }
    }
}