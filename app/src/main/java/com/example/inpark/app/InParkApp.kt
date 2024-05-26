package com.example.inpark.app

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.inpark.data.api.AuthApi
import com.example.inpark.data.api.ParkingApi
import com.example.inpark.screens.Bookings
import com.example.inpark.screens.Home
import com.example.inpark.screens.Maps
import com.example.inpark.screens.Profile
import com.example.inpark.screens.SignIn
import com.example.inpark.screens.Signup
import com.example.inpark.screens.parkingDetails
import com.example.inpark.repository.AuthRepository
import com.example.inpark.repository.ParkingRepository
import com.example.inpark.viewModels.AuthViewModel
import com.example.inpark.viewModels.LocationViewModel
import com.example.inpark.viewModels.ParkingViewModel
import com.example.inpark.viewModels.SignInViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InParkApp(viewModel: LocationViewModel){
    val navController = rememberNavController()
    val context = LocalContext.current
    val authApi = AuthApi.createEndpoint()
    var startDest: String by remember {
        mutableStateOf("")
    }

    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getString("id" , null)
    if (userId != null) {
        startDest = "maps"
    }else {
        startDest = "signin"
    }
    val parkingApi = ParkingApi.createEndpoint()
    val authRepository by lazy { AuthRepository(authApi) }
    val parkingRepository by lazy { ParkingRepository(parkingApi) }
    val authViewModel = AuthViewModel.Factory(authRepository).create(AuthViewModel::class.java)
    val signInViewModel = SignInViewModel.Factory(authRepository).create(SignInViewModel::class.java)
    val parkingViewModel = ParkingViewModel.Factory(parkingRepository).create(ParkingViewModel::class.java)
    NavHost(navController = navController, startDestination = startDest) {
        composable("signup") { Signup(navController,authViewModel = authViewModel) }
        composable("signin") { SignIn(navController, authViewModel = authViewModel, signInViewModel = signInViewModel) }
        composable("parkings/{parkingId}"){ backStackEntry -> val parkingId = backStackEntry.arguments?.getString("parkingId")
                        parkingDetails(parkingViewModel,parkingId!!,navController)
                    }
        composable("home") { Home(navController,parkingViewModel = parkingViewModel,locationViewModel = viewModel) }
        composable("maps"){ Maps(navController, parkingViewModel) }
        composable("bookings"){ Bookings(navController,parkingViewModel = parkingViewModel,locationViewModel = viewModel ) }
        composable("profile"){ Profile(sharedPreferences,navController,authViewModel) }
    }
}



