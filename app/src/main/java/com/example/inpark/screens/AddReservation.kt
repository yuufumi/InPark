package com.example.inpark.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.inpark.R
import com.example.inpark.components.AddProgress
import com.example.inpark.components.Rate
import com.example.inpark.components.ReservationInfoDatePicker
import com.example.inpark.components.ReservationInfoHourPicker
import com.example.inpark.components.ReservationInfoPlacePicker
import com.example.inpark.components.Title
import com.example.inpark.components.UserInfoTextField
import com.example.inpark.data.model.Parking
import com.example.inpark.data.model.Place
import com.example.inpark.data.model.Reservation
import com.example.inpark.data.model.ReservationRequest
import com.example.inpark.data.model.User
import com.example.inpark.outfitFamily
import com.example.inpark.utils.DateUtils
import com.example.inpark.utils.convertToDate
import com.example.inpark.utils.convertToISODateTime
import com.example.inpark.utils.reformatTime
import com.lightspark.composeqr.QrCodeColors
import com.lightspark.composeqr.QrCodeView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddReservation(parkingViewModel: ParkingViewModel,reservationViewModel: ReservationViewModel, parkingId: String, navController: NavController) {
    val slotsByParkingResponse by parkingViewModel.placesPerParkingResponse.observeAsState()
    val parkingById by parkingViewModel.parkingByIdResponse.observeAsState()
    val createResevationResponse by reservationViewModel.createReservationResponse.observeAsState()
    val loading by parkingViewModel.loadingSlots
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
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
        parkingViewModel.getParkingById(parkingId)

    }
    Log.d("loading state", loading.toString())

    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xffA0F000))
        }
    } else {

        var slots: List<Place>? = slotsByParkingResponse?.body()
        Log.d("slots", slots.toString())
        val parkingSlot = Place(0, 1, "A", false, 2)
        val selectedItem = remember { mutableStateOf(parkingSlot) }
        Scaffold(
            bottomBar = {
                StickyBottomBar(10) {
                    if (entryDateState.value.isEmpty() || exitDateState.value.isEmpty() || entryHourState.value.isEmpty() ||
                        exitHourState.value.isEmpty()
                    ) {
                        showToast("Please fill in all the fields")
                    } else {
                        val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                        val date1 = formatter.parse(entryDateState.value)
                        val date2 = formatter.parse(exitDateState.value)

                        // Compare dates using compareTo()
                        val result1 = date1.compareTo(date2)

                        if (result1 > 0) {
                            showToast("Please recheck your dates")
                        } else if (result1 == 0) {
                            val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
                            val time1 = timeFormatter.parse(entryHourState.value)
                            val time2 = timeFormatter.parse(exitHourState.value)
                            val result2 = time1.compareTo(time2)
                            if (result2 >= 0) {
                                showToast("Please recheck your times")
                            } else {
                                val reservation = ReservationRequest(
                                    userId = sharedPreferences.getString("id", null)!!.toInt(),
                                    placeId = selectedItem.value.id!!,
                                    date_entree = convertToISODateTime(entryDateState.value),
                                    date_sortie = convertToISODateTime(exitDateState.value),
                                    heure_entree = reformatTime(entryHourState.value),
                                    heure_sortie = reformatTime(exitHourState.value)
                                )

                                reservationViewModel.createReservation(reservation)
                                showToast("Reservation added successfully")
                            }
                        } else {
                            val reservation = ReservationRequest(
                                userId = sharedPreferences.getString("id", null)!!.toInt(),
                                placeId = selectedItem.value.id!!,
                                date_entree = convertToISODateTime(entryDateState.value),
                                date_sortie = convertToISODateTime(exitDateState.value),
                                heure_entree = reformatTime(entryHourState.value),
                                heure_sortie = reformatTime(exitHourState.value)
                            )

                            reservationViewModel.createReservation(reservation)
                            showToast("Reservation added successfully")
                        }
                    }
                }
            },
            containerColor = Color(0xff002020)
        ) {
            if (createResevationResponse != null) {
                showBottomSheet = true
                val parking: Parking = parkingById?.body()!!
                val parkingSlot: Place = selectedItem.value
                val reservation = Reservation(
                    id = createResevationResponse?.body()!!.id,
                    placeId = selectedItem.value.id,
                    userId = sharedPreferences.getString("id", null)!!.toInt(),
                    date_entree = createResevationResponse?.body()!!.date_entree,
                    date_sortie = createResevationResponse?.body()!!.date_sortie,
                    heure_entree = createResevationResponse?.body()!!.heure_entree,
                    heure_sortie = createResevationResponse?.body()!!.heure_sortie
                )
                Log.d("CURR RESERVATION", reservation.toString())
                reservationViewModel.cacheReservation(reservation, parking, parkingSlot)
                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {},
                        contentColor = Color(0xfffffff0),
                        sheetState = sheetState,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    {
                        Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally)
                        {
                            QrCodeView(
                                    data = reservation.toString(),
                                    modifier = Modifier.size(130.dp),
                                    colors = QrCodeColors(
                                        background = Color(0xffffffff),
                                        foreground = Color(0xff003C3C)
                                    )
                            )
                            Spacer(modifier = Modifier.height(30.dp))
                            Row( verticalAlignment = Alignment.CenterVertically) {
                                Icon(painter = painterResource(id = R.drawable.success), modifier = Modifier.size(26.dp), contentDescription = "success", tint = Color(0xff003C3C))
                                Spacer(modifier = Modifier.width(20.dp))
                                Text(text = "Successfully reserved", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 22.sp, color = Color(0xff000000)))
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(text = "From ${convertToDate(reservation.date_entree)} ${reformatTime(reservation.heure_entree)} to ${convertToDate(reservation.date_sortie)} ${reformatTime(reservation.heure_sortie)}", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 18.sp, color = Color(0xff000000)))
                            Spacer(modifier = Modifier.height(20.dp))
                            Button(
                                modifier = Modifier.fillMaxWidth(0.6f),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff003C3C)),
                                onClick = {
                                    showBottomSheet = false
                                    navController.navigate("home")
                                }) {
                                Text(
                                    text = "Back To Home",
                                    style = TextStyle(
                                        fontFamily = outfitFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp,
                                        color = Color(0xfffffff0)
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(60.dp))
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(
                        rememberScrollState()
                    )
            )
            {
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
                    if (slots != null) {
                        ReservationInfoPlacePicker(
                            items = slots!!,//listOf(selectedItem!!,selectedItem!!,selectedItem!!),
                            selectedItem = selectedItem!!.value,
                            onItemSelected = {
                                selectedItem.value = it
                                Log.d("SELECTED ITEM", it.toString())
                            }
                        )
                    }

                }

            }
        }

    }
}