package com.example.inpark.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.inpark.components.InparkBottomNavBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xff003C3C),
    ) {
        Scaffold(
            containerColor = Color(0xff002020),
            bottomBar = { InparkBottomNavBar(navController = navController, Color(0xff002020)) }
        ) {
            Text(text="profile")
        }
    }
}