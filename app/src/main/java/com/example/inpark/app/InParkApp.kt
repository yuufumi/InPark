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
import com.example.inpark.screens.parkingDetails
import com.example.inpark.models.Parking
import com.example.inpark.utils.GoogleAuthUIClient
import com.example.inpark.utils.SignInState
import com.example.inpark.viewModels.signInViewModel
import com.google.android.gms.auth.api.identity.Identity


@Composable
fun InParkApp(context: Context){
    val navController = rememberNavController()
    val cardData = listOf(
        Parking(1, "Lot A", "Large parking lot near main entrance Large parking lot near main entrance Large parking lot near main entrance Large parking lot near main entrance Large parking lot near main entrance Large parking lot near main entranceLarge parking lot near main entrance Large parking lot near main entrance", 3.00, "Mall", true),
        Parking(2, "Garage B", "Multi-level parking garage", 4.00, "Downtown", false),
        Parking(3, "Street Parking", "Metered street parking on Elm Street", 2.00, "City Center", true),
        Parking(4, "Valet Parking", "Convenient valet service at the restaurant", 10.00, "Restaurant District", true),
        Parking(5, "Employee Lot", "Reserved parking for employees only", 0.00, "Company HQ", false),
        Parking(6, "Motorcycle Parking", "Designated motorcycle parking area", 1.50, "Stadium", true),
        Parking(7, "Permit Parking", "Requires a residential parking permit", 0.00, "Neighborhood", true),
        Parking(8, "Short-Term Parking", "Limited to 30 minutes", 5.00, "Airport", true),
        Parking(9, "Overnight Parking", "Permitted for overnight stays", 8.00, "Hotel", true),
        Parking(10, "Handicap Parking", "Accessible parking spaces", 0.00, "Hospital", true)
    )
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
                NavHost(navController = navController, startDestination = "bookings") {
                    composable("signup") {Signup(navController) }
                    composable("signin") {SignIn(navController)}
                    composable("parkings/{parkingId}"){ backStackEntry ->
                        val parkingId = backStackEntry.arguments?.getString("parkingId")
                        parkingDetails(cardData,parkingId!!,navController)}
                    composable("home") {Home(navController)}
                    composable("maps"){Maps(context,navController)}
                    composable("bookings"){Bookings(navController)}
                    composable("profile"){Profile(navController) }
                }
            }

        }
    }
}


