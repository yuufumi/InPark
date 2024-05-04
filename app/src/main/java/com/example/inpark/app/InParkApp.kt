package com.example.inpark.app

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.media.Image
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.lifecycleScope
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
import com.example.inpark.utils.GoogleAuthUIClient
import com.example.inpark.utils.SignInState
import com.example.inpark.viewModels.signInViewModel
import com.google.android.gms.auth.api.identity.Identity


@Composable
fun InParkApp(){
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xff003C3C),
    ) {
        Scaffold(
            bottomBar = { InparkBottomNavBar(navController = navController,Color(0xff002020)) }
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xff002020))

            ){
                NavHost(navController = navController, startDestination = "home") {
                    composable("signup") {Signup(navController) }
                    composable("signin") {SignIn(navController)}
                    composable("home") {Home(navController)}
                    composable("maps"){Maps(navController)}
                    composable("bookings"){Bookings(navController)}
                    composable("profile"){Profile(navController) }
                }
            }

        }
    }
}


