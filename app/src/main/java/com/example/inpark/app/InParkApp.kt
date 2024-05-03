package com.example.inpark.app

import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inpark.components.InparkBottomNavBar
import com.example.inpark.screens.Bookings
import com.example.inpark.screens.Home
import com.example.inpark.screens.Maps
import com.example.inpark.screens.Profile
import com.example.inpark.screens.SignIn
import com.example.inpark.screens.Signup




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InParkApp() {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xff003C3C),
    ) {
        Scaffold(
            bottomBar = { InparkBottomNavBar(navController = navController,Color(0xff002020)) }
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().background(Color(0xff002020))
            ){
                Text("InParkAPP")
                NavHost(navController = navController, startDestination = "home") {
                    composable("signup") { Signup(navController) }
                    composable("signin") { SignIn(navController) }
                    composable("home") {
                        Home(navController)
                    }
                    composable("maps"){
                        Maps(navController)

                    }
                    composable("bookings"){
                        Bookings(navController)

                    }
                    composable("profile"){

                        Profile(navController)

                    }
            }


            }

        }
    }
}


