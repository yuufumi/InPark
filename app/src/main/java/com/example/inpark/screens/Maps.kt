package com.example.inpark.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.LocationManager
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.inpark.R
import com.example.inpark.components.InparkBottomNavBar
import com.example.inpark.components.Pricing
import com.example.inpark.components.Rate
import com.example.inpark.components.Rating
import com.example.inpark.components.Title
import com.example.inpark.components.TopBar
import com.example.inpark.components.mapSearchBar
import com.example.inpark.data.api.types.AuthRequest
import com.example.inpark.data.model.Parking
import com.example.inpark.databinding.ActivityMapsBinding
import com.example.inpark.outfitFamily
import com.example.inpark.utils.AppPermissions
import com.example.inpark.utils.extractHour
import com.example.inpark.utils.getCurrentLocation
import com.example.inpark.viewModels.ParkingViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.lightspark.composeqr.QrCodeColors
import com.lightspark.composeqr.QrCodeView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Maps(navController: NavController, parkingViewModel: ParkingViewModel) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var locationManager: LocationManager
    val context = LocalContext.current
    var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    var showMap by remember { mutableStateOf(false) }
    var location by remember { mutableStateOf(LatLng(36.6993,3.1755)) }
    var mapProperties by remember { mutableStateOf(MapProperties()) }
    var changeIcon by remember { mutableStateOf(false) }
    var selectedParking:Parking? by remember {
        mutableStateOf(null)
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }
    val allParkingsResponse by parkingViewModel.allParkingsResponse.observeAsState()
    LaunchedEffect(Unit) {
        parkingViewModel.getAllParkings()
    }
    var parkings: List<Parking>? = allParkingsResponse?.body()
    val onMarkerClick: () -> Unit = {
    showBottomSheet = true
    }
    Surface(
    modifier = Modifier
        .fillMaxSize()
        .background(Color(0xff002020)),
    color = Color(0xff003C3C)
) {
    Scaffold(        containerColor = Color(0xff002020),bottomBar = { InparkBottomNavBar(navController = navController,Color(0xff002020)) }) {
        getCurrentLocation(context) {
            location = it
            showMap = true
        }
        if (showMap) {

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ){
                parkings?.forEach { parking ->
                    val markerState = rememberMarkerState(
                        position = LatLng(parking.latitude.toDouble(), parking.longitude.toDouble()),
                    )
                    Marker(
                        state = markerState,
                        title = "Parking Location",
                        icon = bitmapDescriptorFromVector(context, R.drawable.map_maps,desiredWidth = 96,desiredHeight = 96),
                        onClick = { showBottomSheet = true
                            selectedParking = parking
                        true
                        }
                    )


                    }
                }
            if(showBottomSheet){
                ModalBottomSheet(onDismissRequest = {showBottomSheet = false},contentColor = Color(0xfffffff0),sheetState = sheetState, modifier = Modifier
                    .fillMaxWidth())
                {
                    Column(horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Row(
                            modifier = Modifier

                                .padding(horizontal = 25.dp),
                            horizontalArrangement = Arrangement.spacedBy(15.dp)
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .size(width = 100.dp, height = 100.dp)
                                    .background(color = Color.Transparent)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                            {
                                // Add your content here (e.g., Text, Column, etc.)
                                Image(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .aspectRatio(1f),
                                    contentScale = ContentScale.Crop,
                                    // Fills the container (adjust as// Resize behavior
                                    painter = painterResource(id = R.drawable.parking_example), // Replace with your image resource
                                    contentDescription = "parking Image" // Optional content description for accessibility
                                )
                            }
                            Column()
                            {
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                )
                                {
                                    Text(
                                        text = selectedParking!!.nom,
                                        modifier = Modifier.padding(start = 3.dp),
                                        style = TextStyle(
                                            fontFamily = outfitFamily,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = Color(0xff000000)
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Text(
                                        text = "${selectedParking!!.price_per_hour}Da/hr",
                                        modifier = Modifier.padding(start = 3.dp),
                                        style = TextStyle(
                                            fontFamily = outfitFamily,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 12.sp,
                                            color = Color(0xff000000)
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.height(15.dp))
                                Row(verticalAlignment = Alignment.CenterVertically)
                                {
                                    Rate(selectedParking!!.rating.toDouble())
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Divider(
                                        modifier = Modifier
                                            .height(20.dp)
                                            .width(2.dp), color = Color(0xff002020)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = selectedParking!!.location,
                                        modifier = Modifier.padding(start = 3.dp),
                                        style = TextStyle(
                                            fontFamily = outfitFamily,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 14.sp,
                                            color = Color(0x5f000000)
                                        )
                                    )
                                }

                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(1.dp), color = Color(0xff002020)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                text = "Available",
                                style = TextStyle(
                                    fontFamily = outfitFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp,
                                    color = Color(0x5f143C3C)
                                )
                            )
                            Row (verticalAlignment = Alignment.CenterVertically){
                                freeSpaces(free = 10)
                                Spacer(modifier = Modifier.width(10.dp))
                                Divider(modifier = Modifier
                                    .height(24.dp)
                                    .width(1.dp),color = Color(0xff0A3622))
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "${extractHour(selectedParking!!.openning_hour)} h - ${extractHour(selectedParking!!.closing_hour)} h" ,
                                    style = TextStyle(
                                        fontFamily = outfitFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp,
                                        color = Color(0xff143C3C)
                                    )
                                )
                            }

                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                            Button(modifier = Modifier.fillMaxWidth(0.3f), shape = RoundedCornerShape(10.dp),onClick = {navController.navigate("parkings/${selectedParking!!.id}")}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xff003C3C))) {
                                Text(text="Reserve", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color(0xfffffff0)))
                            }
                            Button(shape = RoundedCornerShape(10.dp),onClick = {navController.navigate("parkings/${selectedParking!!.id}")}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xff087990))) {
                                Text(text="Details", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color(0xfffffff0)))
                            }
                        }
                        Spacer(modifier = Modifier.height(60.dp))
                    }
                }

            }
        }

        else {
            Text(text = "Loading Map...")

        }
        mapSearchBar(navController)
    }
    }
}

@Composable
fun bitmapDescriptorFromVector(context: Context,
    @DrawableRes vectorResourceId: Int,    desiredWidth: Int,
                               desiredHeight: Int
): BitmapDescriptor {
    val vectorBitmap = vectorToBitmap(context, vectorResourceId,desiredWidth,desiredHeight)
    return BitmapDescriptorFactory.fromBitmap(vectorBitmap)
}

fun vectorToBitmap(context: Context, @DrawableRes vectorResourceId: Int,   desiredWidth: Int,desiredHeight: Int): Bitmap {
    val vectorDrawable = ResourcesCompat.getDrawable(context.resources, vectorResourceId, null)
    val bitmap = Bitmap.createBitmap(
        desiredWidth,
        desiredHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    vectorDrawable?.setBounds(0, 0, canvas.width, canvas.height)
    vectorDrawable?.draw(canvas)
    return bitmap
}
@Composable
fun freeSpaces(free: Int){
    Row(verticalAlignment = Alignment.CenterVertically){
        Icon(painter = painterResource(id = R.drawable.free_space), modifier = Modifier.size(20.dp), tint = Color(0xff0A3622),contentDescription = "space")
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "${free.toString()} free spaces", style = TextStyle(fontFamily = outfitFamily, fontWeight = FontWeight.Medium, fontSize = 16.sp, color = Color(0xff0A3622)))
    }
}