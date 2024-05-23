package com.example.inpark





import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inpark.app.InParkApp
import com.example.inpark.data.InParkApplication
import com.example.inpark.data.api.AuthApi
import com.example.inpark.data.api.ParkingApi
import com.example.inpark.repository.AuthRepository
import com.example.inpark.repository.ParkingRepository
import com.example.inpark.screens.Bookings
import com.example.inpark.screens.Home
import com.example.inpark.screens.Maps
import com.example.inpark.screens.MapsActivity
import com.example.inpark.screens.Profile
import com.example.inpark.screens.SignIn
import com.example.inpark.screens.Signup
import com.example.inpark.screens.parkingDetails

import com.example.inpark.ui.theme.InParkTheme
import com.example.inpark.viewModels.AuthViewModel
import com.example.inpark.viewModels.ParkingViewModel
import com.example.inpark.viewModels.UserviewModel

val outfitFamily = FontFamily(
    Font(R.font.outfit_light, FontWeight.Light),
    Font(R.font.outfit_regular, FontWeight.Normal),
    Font(R.font.outfit_medium, FontWeight.Medium),
    Font(R.font.outfit_bold, FontWeight.Bold)
)

class MainActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xff002020),
            ) {
            InParkApp()

            }

        }
            }
        }

@Composable
fun Greeting() {
    val context = LocalContext.current
    Button(onClick = {
        context.startActivity(Intent(context,MapsActivity::class.java))
    }){
        Text(text = "goto maps")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InParkTheme {
        Greeting()
    }
}