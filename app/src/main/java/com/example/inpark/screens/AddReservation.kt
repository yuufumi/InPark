package com.example.inpark.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.room.util.TableInfo
import com.example.inpark.components.BookingForm
import com.example.inpark.components.StickyBottomBar
import com.example.inpark.viewModels.ParkingViewModel
import com.example.inpark.viewModels.ReservationViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inpark.components.AddProgress
import com.example.inpark.components.ReservationInfoDatePicker
import com.example.inpark.components.ReservationInfoHourPicker
import com.example.inpark.components.ReservationInfoPlacePicker
import com.example.inpark.components.Title
import com.example.inpark.components.UserInfoTextField
import com.example.inpark.data.model.Parking
import com.example.inpark.data.model.ParkingSlot
import com.example.inpark.data.model.Reservation
import com.example.inpark.data.model.ReservationRequest
import com.example.inpark.data.model.User
import com.example.inpark.outfitFamily
import com.example.inpark.utils.DateUtils
import com.example.inpark.utils.convertToISODateTime
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddReservation(parkingViewModel: ParkingViewModel,reservationViewModel: ReservationViewModel, parkingId: String, navController: NavController) {
    val slotsByParkingResponse by parkingViewModel.placesPerParkingResponse.observeAsState()
    val loading by parkingViewModel.loadingSlots

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val entryDateState = remember {
        mutableStateOf("")
    }
    val exitDateState = remember {
        mutableStateOf("")
    }
    val entryHourState = remember {
        mutableStateOf("")
    }
    val exitHourState = remember {
        mutableStateOf("")
    }
    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    LaunchedEffect(Unit) {
        parkingViewModel.getSlotsByParking(parkingId)

    }
    Log.d("loading state",loading.toString())

    if(loading){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }else {

        var slots: List<ParkingSlot>? = slotsByParkingResponse?.body()
        Log.d("slots",slots.toString())
        val parkingSlot = ParkingSlot(0,1,"A",false,2)
        val selectedItem = remember { mutableStateOf(parkingSlot) }
        Scaffold(
            bottomBar = {StickyBottomBar(10) {
                if (entryDateState.value.isEmpty() || exitDateState.value.isEmpty() || entryHourState.value.isEmpty() ||
                    exitHourState.value.isEmpty()
                ) {
                    showToast("Please fill in all the fields")
                } else {

                    val reservation = ReservationRequest(
                        userId = sharedPreferences.getString("id", null)!!.toInt(),
                        placeId = selectedItem.value.id!!,
                        date_entree = convertToISODateTime(entryDateState.value),
                        date_sortie = convertToISODateTime(exitDateState.value),
                        heure_entree = entryHourState.value,
                        heure_sortie = exitHourState.value
                    )
                    reservationViewModel.createReservation(reservation)
                    showToast("Reservation created successfully")
                }
            }
            } ,
            containerColor = Color(0xff002020)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                parkingDetails(
                    parkingViewModel = parkingViewModel,
                    parkingId = parkingId,
                    navController = navController
                )
                var selectedStartDate by remember { mutableStateOf<LocalDate?>(null) }
                Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 30.dp, end = 30.dp, bottom = 100.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Enter your booking infos",
                            style = TextStyle(
                                fontFamily = outfitFamily,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start,
                                fontSize = 20.sp,
                                color = Color(0xfffffff0)
                            )
                        )
                        ReservationInfoDatePicker(
                            label = "Entry Date", date = entryDateState
                        )
                        ReservationInfoDatePicker(
                            label = "Exit Date", date = exitDateState
                        )
                        ReservationInfoHourPicker(
                            label = "Entry hour", time = entryHourState
                        )
                        ReservationInfoHourPicker(
                            label = "Exit hour", time = exitHourState
                        )
                    if(slots != null){
                        ReservationInfoPlacePicker(
                            items = slots!!,//listOf(selectedItem!!,selectedItem!!,selectedItem!!),
                            selectedItem = selectedItem!!.value,
                            onItemSelected = { selectedItem.value = it
                            Log.d("SELECTED ITEM",it.toString())}
                        )
                    }

                    }

                }
            }
        }
    }