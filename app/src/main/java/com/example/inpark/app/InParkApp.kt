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
import androidx.activity.viewModels
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inpark.components.InparkBottomNavBar
import com.example.inpark.data.InParkApplication
import com.example.inpark.data.api.AuthApi
import com.example.inpark.data.api.ParkingApi
import com.example.inpark.screens.Bookings
import com.example.inpark.screens.Home
import com.example.inpark.screens.Maps
import com.example.inpark.screens.Profile
import com.example.inpark.screens.SignIn
import com.example.inpark.screens.Signup
import com.example.inpark.screens.parkingDetails
import com.example.inpark.models.Parking
import com.example.inpark.repository.AuthRepository
import com.example.inpark.repository.ParkingRepository
import com.example.inpark.utils.GoogleAuthUIClient
import com.example.inpark.utils.SignInState
import com.example.inpark.viewModels.AuthViewModel
import com.example.inpark.viewModels.ParkingViewModel
import com.example.inpark.viewModels.UserviewModel
import com.example.inpark.viewModels.signInViewModel
import com.google.android.gms.auth.api.identity.Identity


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InParkApp(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val authApi = AuthApi.createEndpoint()
    var startDest: String by remember {
        mutableStateOf("")
    }

    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getString("id" , null)
    if (userId != null) {
        startDest = "home"
    }else {
        startDest = "signin"
    }
    val parkingApi = ParkingApi.createEndpoint()
    val authRepository by lazy { AuthRepository(authApi) }
    val parkingRepository by lazy { ParkingRepository(parkingApi) }
    val authViewModel = AuthViewModel.Factory(authRepository).create(AuthViewModel::class.java)
    val parkingViewModel = ParkingViewModel.Factory(parkingRepository).create(ParkingViewModel::class.java)
    NavHost(navController = navController, startDestination = startDest) {
        composable("signup") { Signup(navController,authViewModel = authViewModel) }
        composable("signin") { SignIn(navController, authViewModel = authViewModel) }
        composable("parkings/{parkingId}"){ backStackEntry -> val parkingId = backStackEntry.arguments?.getString("parkingId")
                        parkingDetails(parkingViewModel,parkingId!!,navController)
                    }
        composable("home") { Home(navController,parkingViewModel = parkingViewModel) }
        composable("maps"){ Maps(navController) }
        composable("bookings"){ Bookings(navController) }
        composable("profile"){ Profile(sharedPreferences,navController,authViewModel) }
    }
}



