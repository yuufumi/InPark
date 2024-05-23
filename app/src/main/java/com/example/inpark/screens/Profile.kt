package com.example.inpark.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inpark.components.GoogleButton
import com.example.inpark.components.InparkBottomNavBar
import com.example.inpark.components.Title
import com.example.inpark.viewModels.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile(sharedPreferences: SharedPreferences, navController: NavController,authViewModel: AuthViewModel) {
    val userId = sharedPreferences.getString("id" , null)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xff003C3C),
    ) {
        Scaffold(
            containerColor = Color(0xff002020),
            bottomBar = { InparkBottomNavBar(navController = navController, Color(0xff002020)) }
        ) {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 28.dp)
                .verticalScroll(rememberScrollState())
            ) {
                GoogleButton(onClick = {
                    sharedPreferences.edit().remove("id").apply()
                    sharedPreferences.edit().remove("username").apply()
                    authViewModel.logoutUser()
                    navController.navigate("signin")
                })
                if(userId != null){
                    val username = sharedPreferences.getString("username",null)
                    if (username != null) {
                        Title(value = username)
                    }
                }else {
                    Title(value = "no user authenticated")
                }
            }
            }
        }
    }