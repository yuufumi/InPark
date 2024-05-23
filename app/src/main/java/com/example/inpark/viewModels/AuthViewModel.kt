package com.example.inpark.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inpark.data.api.types.AuthRequest
import com.example.inpark.data.api.types.AuthResponse
import com.example.inpark.data.model.User
import com.example.inpark.repository.AuthRepository
import retrofit2.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.RequestBody

class AuthViewModel (private val authRepository: AuthRepository) : ViewModel() {
    private val _signupResponse = MutableLiveData<Response<User>>()
    val signupResponse : LiveData<Response<User>> get() = _signupResponse

    private val _loginResponse = MutableLiveData<Response<User>?>()
    val loginResponse : LiveData<Response<User>?> get() = _loginResponse

    private val _allUsersResponse = MutableLiveData<Response<List<User>>>()


    val allUsersResponse : LiveData<Response<List<User>>> get() = _allUsersResponse
    val loading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    fun signupUser(user: User) {
        loading.value = true
        error.value = null

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = authRepository.registerUser(user)
                loading.value = false
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _signupResponse.value = response
                    }
                    Log.d("AuthViewModel", "Register user success: ${response.body()}")
                } else {
                    error.value = "Failed to register user: ${response.message()}"
                }
            } catch (e:Exception) {
                Log.e("AuthViewModel", "Register user error", e)
                error.value = "Failed to register user: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }

    fun loginUser(user: AuthRequest) {
        loading.value = true
        error.value = null

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = authRepository.loginUser(user)
                loading.value = false
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _loginResponse.value = response
                    }
                    Log.d("AuthViewModel", "Login user success: ${response.body()}")
                } else {
                    error.value = "Failed to login user: ${response.message()}"
                }
            } catch (e:Exception) {
                Log.e("AuthViewModel", "login user error", e)
                error.value = "Failed to login user: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }

    fun getAllUsers(){
        loading.value = true
        error.value = null

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = authRepository.getAllUsers()
                loading.value = false
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        _allUsersResponse.value = response
                    }
                    Log.d("AuthViewModel", "Login user success: ${response.body()}")
                } else {
                    error.value = "Failed to login user: ${response.message()}"
                }
            } catch (e:Exception) {
                Log.e("AuthViewModel", "login user error", e)
                error.value = "Failed to login user: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }

    fun logoutUser(){
        _loginResponse.value = null
    }
    class Factory(private val authRepository: AuthRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AuthViewModel(authRepository) as T
        }
    }
}