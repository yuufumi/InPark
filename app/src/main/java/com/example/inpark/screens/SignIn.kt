package com.example.inpark.screens

import android.app.Activity
import android.content.Context
import android.credentials.CredentialManager
import android.credentials.GetCredentialRequest
import android.credentials.GetCredentialResponse
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
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
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.credentials.CustomCredential
import androidx.navigation.NavController
import com.example.inpark.components.AddProgress
import com.example.inpark.components.CustomButton
import com.example.inpark.components.GoogleButton
import com.example.inpark.components.Title
import androidx.compose.material3.Button
import com.example.inpark.components.UserInfoTextField
import com.example.inpark.data.api.types.AuthRequest
import com.example.inpark.data.model.User
import com.example.inpark.outfitFamily
import com.example.inpark.viewModels.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import com.example.inpark.data.api.types.EmailRequest
import com.example.inpark.data.dao.UserDao
import com.example.inpark.utils.AuthResult
import com.example.inpark.viewModels.SignInViewModel
import com.google.android.gms.common.api.ApiException
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import kotlinx.coroutines.launch

@Composable
fun SignIn (navController: NavController, authViewModel: AuthViewModel, signInViewModel: SignInViewModel,userDao: UserDao) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    var text by remember { mutableStateOf<String?>(null) }
    val user by remember(signInViewModel) { signInViewModel.user }.collectAsState()
    val signInRequestCode = 1
    val state = rememberOneTapSignInState()

    val emailResponse by authViewModel.getByEmailResponse.observeAsState()
    val authResultLauncher = rememberLauncherForActivityResult(contract = AuthResult()) { task ->
        try {
            val account = task?.getResult(ApiException::class.java)
            if (account == null) {
                text = "Google Sign In Failed"
            } else {
                authViewModel.getByEmail(account.email!!)
                if(emailResponse == null){
                    Log.e("valeur ta3ha", emailResponse.toString())
                }else {
                    scope.launch {
                        signInViewModel.setSignInValue(
                            email = emailResponse!!.body()!!.email,
                            username = emailResponse!!.body()!!.username,
                            nom = emailResponse!!.body()!!.nom,
                            prenom = emailResponse!!.body()!!.prenom,
                            mot_de_passe = emailResponse!!.body()!!.prenom,
                            num_telephone = emailResponse!!.body()!!.num_telephone,
                            id = emailResponse!!.body()!!.id
                        )
                    }
                }
            }
        } catch (e: ApiException) {
            text = e.localizedMessage
        }
    }


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


    val onGoogleClick: () -> Unit = {
        authResultLauncher.launch(signInRequestCode)
    }
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
            if (!isLoggedIn) {
                if(loginResponse != null){
                    Log.d("USER ID FROM SERVER",loginResponse?.body()?.id.toString())
                    sharedPreferences.edit().putString("id", loginResponse?.body()?.id.toString())
                        .apply()
                    sharedPreferences.edit()
                        .putString("username", loginResponse?.body()?.username.toString()).apply()
                    authViewModel.addUser(loginResponse?.body()!!)
                    navController.navigate("home")
                    isLoggedIn =true
                }else if(emailResponse != null){
                    Log.d("USER ID FROM SERVER",emailResponse?.body()?.id.toString())
                    sharedPreferences.edit().putString("id", emailResponse?.body()?.id.toString())
                        .apply()
                    sharedPreferences.edit()
                        .putString("username", emailResponse?.body()?.username.toString()).apply()
                    authViewModel.addUser(emailResponse?.body()!!)
                    navController.navigate("home")
                    isLoggedIn =true
                }

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

//@Composable
//fun AuthView(
//    errorText: String?,
//    onClick: () -> Unit
//) {
//    var isLoading by remember { mutableStateOf(false) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    androidx.compose.material.Text(
//                        text = "Google Sign In",
//                        modifier = Modifier.fillMaxWidth(),
//                        textAlign = TextAlign.Center
//                    )
//                }
//            )
//        }
//    ) {innerPadding ->
//        Column(
//            modifier = Modifier.fillMaxSize().padding(innerPadding),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            GoogleSignInButton(
//                text = "Sign In with Google",
//                icon = painterResource(id = R.drawable.google_sign_in_btn),
//                loadingText = "Signing In...",
//                isLoading = isLoading,
//                onClick = {
//                    isLoading = true
//                    onClick()
//                }
//            )
//
//            errorText?.let {
//                isLoading = false
//
//                Spacer(modifier = Modifier.height(30.dp))
//
//                androidx.compose.material.Text(
//                    text = it
//                )
//            }
//        }
//    }
//}

@Composable
fun getGoogleSignInOptions(): GoogleSignInOptions {
    val context = LocalContext.current
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("YOUR_GOOGLE_CLIENT_ID")
        .requestEmail()
        .build()
    return gso
}

fun sendIdTokenToBackend(idToken: String) {
    // ...
}