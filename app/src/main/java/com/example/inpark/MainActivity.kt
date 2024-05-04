package com.example.inpark

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inpark.R.font.outfit_black
import com.example.inpark.app.InParkApp
import com.example.inpark.components.InparkBottomNavBar
import com.example.inpark.screens.Bookings
import com.example.inpark.screens.Home
import com.example.inpark.screens.Maps
import com.example.inpark.screens.Profile
import com.example.inpark.screens.SignIn
import com.example.inpark.screens.Signup
import com.example.inpark.ui.theme.InParkTheme
import com.example.inpark.utils.AppPermissions
import com.example.inpark.utils.GoogleAuthUIClient
import com.example.inpark.viewModels.signInViewModel
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

val outfitFamily = FontFamily(
    Font(R.font.outfit_light, FontWeight.Light),
    Font(R.font.outfit_regular, FontWeight.Normal),
    Font(R.font.outfit_medium, FontWeight.Medium),
    Font(R.font.outfit_bold, FontWeight.Bold)
)

class MainActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        val googleAuthUIClient by lazy {
            GoogleAuthUIClient(
                context = applicationContext,
                oneTapClient = Identity.getSignInClient(applicationContext)

            )
        }

        super.onCreate(savedInstanceState)
        val permission = AppPermissions()
        if (permission.isLocationOk(this)) {
            println("Allowed")
        } else {
            permission.requestLocationPermission(this)
            println("denied")
        }
        installSplashScreen()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xff003C3C),
            ) {
                InParkApp()
            }

        }
            }
        }

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Text(
        text = "Hello $name!",
        fontFamily = outfitFamily,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InParkTheme {
        Greeting("Android")
    }
}