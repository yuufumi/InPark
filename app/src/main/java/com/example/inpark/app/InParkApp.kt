package com.example.inpark.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inpark.screens.Home
import com.example.inpark.screens.SignIn
import com.example.inpark.screens.Signup

@Composable
fun InParkApp(){
    val navController = rememberNavController()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xff003C3C),
        ) {
            Column (
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
            ){
                NavHost(navController = navController, startDestination = "signin") {
                    composable("signup") { Signup(navController) }
                    composable("signin") { SignIn(navController) }
                    composable("home") { Home(navController) }
                }
            }

        }
}