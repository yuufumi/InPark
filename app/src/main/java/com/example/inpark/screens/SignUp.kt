package com.example.inpark.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.inpark.components.AddProgress
import com.example.inpark.components.CustomButton
import com.example.inpark.components.Title
import com.example.inpark.components.UserInfoTextField
import com.example.inpark.data.model.User
import com.example.inpark.data.model.signUpState

import com.example.inpark.outfitFamily
import com.example.inpark.viewModels.AuthViewModel
import com.example.inpark.viewModels.UserviewModel
import com.google.firebase.auth.UserInfo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
fun Signup(navController: NavController, authViewModel: AuthViewModel){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    val signupResponse by authViewModel.signupResponse.observeAsState()
    var SignedUp by remember {
        mutableStateOf(false)
    }


    val usernameState = remember {
        mutableStateOf("")
    }

    val firstNameState = remember {
        mutableStateOf("")
    }
    val lastNameState = remember {
        mutableStateOf("")
    }
    val emailState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }
    val confirmState = remember {
        mutableStateOf("")
    }
    val phoneState = remember {
        mutableStateOf("")
    }


    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    val onSignUpClick:()-> Unit= {
        // Check if any of the fields is empty
        if (lastNameState.value.isEmpty() || firstNameState.value.isEmpty() || usernameState.value.isEmpty() ||
            emailState.value.isEmpty() || passwordState.value.isEmpty() || phoneState.value.isEmpty()
            || passwordState.value.isEmpty() || confirmState.value.isEmpty()) {
            showToast("Please fill in all the fields")
        } else if (passwordState.value != confirmState.value) {
            showToast("Passwords do not match")

        } else {
            val user = User(
                nom = lastNameState.value,
                prenom = firstNameState.value,
                username = usernameState.value,
                email = emailState.value,
                mot_de_passe = passwordState.value,
                num_telephone = phoneState.value
            )

            authViewModel.signupUser(user)
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 30.dp, end = 30.dp, top = 40.dp, bottom = 40.dp)
        .verticalScroll(
            rememberScrollState()
        ),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Title(value = "Sign up")
        UserInfoTextField(label = "Username", placeholder = "example: yuufumi", type = "",state = usernameState)
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(30.dp),
        ){
            UserInfoTextField(label = "First name", placeholder = "", type = "",size=0.5f,state = firstNameState)
            UserInfoTextField(label = "Last name", placeholder = "", type = "",size=1f,state = lastNameState)
        }
        UserInfoTextField(label = "Email Address", placeholder = "example@gmail.com", type = "",state = emailState)
        UserInfoTextField(label = "Phone number", placeholder = "0666063622", type = "",state = phoneState)
        UserInfoTextField(label = "Password", placeholder = "", type = "password", state = passwordState)
        UserInfoTextField(label = "Confirm password", placeholder = "", type = "password", state = confirmState)
        CustomButton(label = "Register", onbtnClick = onSignUpClick)
        AddProgress(authViewModel = authViewModel)
        if((signupResponse != null) && !SignedUp){
            sharedPreferences.edit().putString("id", signupResponse?.body()?.userId.toString()).apply()
            navController.navigate("signin")
            SignedUp = true
        }
        Row {
            Text(
                text = "Do you have an account?",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color(0xfffffff0),
                    fontSize = 14.sp,
                    fontFamily = outfitFamily,
                )
            )
            Text(
                modifier = Modifier.clickable(onClick = {navController.navigate("signin")}),
                text = " Sign In",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color(0xFFA0F000),
                    fontSize = 14.sp,
                    fontFamily = outfitFamily,
                    textDecoration = TextDecoration.Underline
                )
            )
        }

    }
}

fun checkPassword(password:String, confirm:String): Boolean{
    return password == confirm
}

