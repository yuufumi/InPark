package com.example.inpark.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inpark.R
import com.example.inpark.components.CustomButton
import com.example.inpark.components.GoogleButton
import com.example.inpark.components.InparkBottomNavBar
import com.example.inpark.components.LogoutButton
import com.example.inpark.components.ParkingCard
import com.example.inpark.components.ProfileTopBar
import com.example.inpark.components.ShimmerListItem
import com.example.inpark.components.TabBar
import com.example.inpark.components.Title
import com.example.inpark.components.TopBar
import com.example.inpark.components.UserInfo
import com.example.inpark.components.UserInfoTextField
import com.example.inpark.data.dao.UserDao
import com.example.inpark.data.model.Parking
import com.example.inpark.data.model.User
import com.example.inpark.outfitFamily
import com.example.inpark.viewModels.AuthViewModel
import com.example.inpark.viewModels.LocationViewModel
import com.example.inpark.viewModels.ParkingViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile(sharedPreferences: SharedPreferences, navController: NavController,authViewModel: AuthViewModel,userDao: UserDao,parkingViewModel:ParkingViewModel) {
    val userId = sharedPreferences.getString("id", null)
    val (selectedTabIndex, setSelectedTabIndex) = remember { mutableStateOf(0) }
    val allParkingsResponse by parkingViewModel.allParkingsResponse.observeAsState()
    var parkings: List<Parking>? = allParkingsResponse?.body()
    val getByIdResponse = authViewModel.getByIdResponse.observeAsState()
    LaunchedEffect(Unit) {
        authViewModel.getById(userId!!)
        parkingViewModel.getAllParkings()
    }
    val user: User? = getByIdResponse.value
    Log.d("USER",user.toString())
    if (user != null) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xff003C3C),
        ) {
            Scaffold(
                containerColor = Color(0xff002020),
                bottomBar = { InparkBottomNavBar(navController = navController, Color(0xff002020)) }
            ) {
                Column(
                    horizontalAlignment = Alignment.Start, modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xff003C3C))
                            .fillMaxWidth()
                            .height(240.dp)
                            .padding(top = 30.dp),
                        contentAlignment = Alignment.TopCenter

                    ) {
                        ProfileTopBar(navController)
                        Icon(
                            painter = painterResource(id = R.drawable.profile),
                            modifier = Modifier
                                .size(120.dp)
                                .align(Alignment.BottomCenter)
                                .offset(y = 40.dp),
                            contentDescription = "back",
                            tint = Color(0xfffffff0)
                        )
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "${user!!.prenom} ${user!!.nom}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 40.dp),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xfffffff0),
                            fontFamily = outfitFamily

                        )
                    )
                    Text(
                        text = "${user!!.email}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 40.dp),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light,
                            color = Color(0xfffffff0),
                            fontFamily = outfitFamily

                        )
                    )
                    TabBar(
                        selectedTabIndex = selectedTabIndex,
                        onTabSelected = setSelectedTabIndex
                    )
                    // Display content based on the selected tab
                    when (selectedTabIndex) {
                        0 ->
                            if(parkings == null){
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 20.dp, bottom = 60.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    for(i in 1..5) {
                                        ShimmerListItem()
                                    }
                                }
                            }else {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 20.dp, bottom = 60.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    parkings!!.forEach { parking ->
                                        ParkingCard(
                                            parking = parking,
                                            navController = navController
                                        )
                                    }
                                }
                            }
                        1 -> Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 30.dp, end = 30.dp, top = 40.dp, bottom = 40.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        UserInfo(label = "Username", value = user!!.username)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(30.dp),
                        ) {
                            UserInfo(label = "First name", value = user!!.prenom, size = 0.5f)
                            UserInfo(label = "Last name", value = user!!.nom, size = 1f)
                        }
                        UserInfo(label = "Email Address", value = user!!.email)
                        UserInfo(label = "Phone number", value = user!!.num_telephone)
                        LogoutButton(onClick = {
                            sharedPreferences.edit().remove("id").apply()
                            sharedPreferences.edit().remove("username").apply()
                            authViewModel.logoutUser(userId!!)
                            navController.navigate("signin")
                        })
                        Spacer(modifier = Modifier.height(60.dp))
                    }

                        }


                }
            }
        }
    } else {
        CircularProgressIndicator()
    }
}