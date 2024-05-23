package com.example.inpark.screens

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inpark.components.AddProgress
import com.example.inpark.components.CustomButton
import com.example.inpark.components.GoogleButton
import com.example.inpark.components.Title
import com.example.inpark.components.UserInfoTextField
import com.example.inpark.data.api.types.AuthRequest
import com.example.inpark.data.model.User
import com.example.inpark.outfitFamily
import com.example.inpark.viewModels.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignIn (navController: NavController, authViewModel: AuthViewModel) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val usernameState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }

    val loginResponse by authViewModel.loginResponse.observeAsState()
    var isLoggedIn by remember {
        mutableStateOf(false)
    }

    val onLoginClick: () -> Unit = {
        val loginRequest = AuthRequest(username = usernameState.value, mot_de_passe = passwordState.value)
        authViewModel.loginUser(loginRequest)
    }

    fun displayUsers(users:List<User>){
        Toast.makeText(context, users[0].toString(), Toast.LENGTH_SHORT).show()
    }
    val onGoogleClick: () -> Unit = {}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp, top = 120.dp)
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Title(value = "Login")
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var username = UserInfoTextField(label = "Username", placeholder = "example: yuufumi", type = "",state = usernameState)
            var password = UserInfoTextField(label = "Password", placeholder = "", type = "password", state = passwordState)
            CustomButton(label = "Login", onbtnClick = onLoginClick)
            AddProgress(authViewModel = authViewModel)
            if ((loginResponse != null) && (!isLoggedIn)) {
                sharedPreferences.edit().putString("id", loginResponse?.body()?.userId.toString())
                    .apply()
                sharedPreferences.edit()
                    .putString("username", loginResponse?.body()?.username.toString()).apply()
                navController.navigate("home")
                isLoggedIn =true
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Divider(
                    modifier = Modifier
                        .height(1.dp) // Adjust height as needed
                        .fillMaxWidth(0.46f)
                )

                Text(
                    text = "or",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 5.dp),
                    style = TextStyle(
                        fontFamily = outfitFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xfffffff0),
                        fontSize = 16.sp
                    )
                )
                Divider(
                    modifier = Modifier
                        .height(1.dp) // Adjust height as needed
                        .fillMaxWidth(1f)
                )


            }
            GoogleButton(onClick = onGoogleClick)
            Row {
                Text(
                    text = "You don’t have an account?",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color(0xfffffff0),
                        fontSize = 14.sp,
                        fontFamily = outfitFamily,
                    )
                )
                Text(
                    modifier = Modifier.clickable(onClick = {navController.navigate("signup")}),
                        text = " Sign up",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color(0xFFA0F000),
                            fontSize = 14.sp,
                            fontFamily = outfitFamily,
                            textDecoration = TextDecoration.Underline
                        ),
                    )



            }

        }
    }
}
