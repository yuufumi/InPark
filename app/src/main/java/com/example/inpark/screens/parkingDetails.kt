package com.example.inpark.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inpark.components.DetailsStickyBottomBar
import com.example.inpark.components.MainParkingDetails
import com.example.inpark.components.ParkingTopBar
import com.example.inpark.components.StickyBottomBar
import com.example.inpark.data.model.Parking
import com.example.inpark.data.model.ReservationRequest
import com.example.inpark.outfitFamily
import com.example.inpark.utils.convertToISODateTime
import com.example.inpark.viewModels.ParkingViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun parkingDetails(parkingViewModel: ParkingViewModel,parkingId: String,navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val parkingResponse by parkingViewModel.parkingByIdResponse.observeAsState()
    LaunchedEffect(Unit) {
        parkingViewModel.getParkingById(parkingId)
    }
    var parking: Parking? = parkingResponse?.body()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    if(parking != null) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 28.dp, bottom = 60.dp)
        ) {

            ParkingTopBar(navController)
            Spacer(modifier = Modifier.height(40.dp))
            MainParkingDetails(parking)
            Spacer(modifier = Modifier.height(20.dp))
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = "Description",
                    style = TextStyle(
                        fontFamily = outfitFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color(0xfffffff0)
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = parking.description,
                    style = TextStyle(
                        fontFamily = outfitFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color(0xfffffff0)
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))


        }
        }
    }

fun <Parking> getParkingById(parkings: List<Parking>, id:Int): Parking? where Parking: Any{
    return parkings.firstOrNull{ it::class.java.getDeclaredField("id").getInt(it) == id }
}