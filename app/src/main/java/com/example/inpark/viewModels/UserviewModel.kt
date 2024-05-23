package com.example.inpark.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.inpark.data.model.User
import com.example.inpark.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserviewModel(private val userRepository: UserRepository): ViewModel() {
    val allUsers = mutableStateOf(listOf<User>())

    fun getAllUsers() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                allUsers.value  = userRepository.getAllUsers()
            }
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    userRepository.addUser(user)
                } catch (e:Exception) {
                    Log.e("addUser(signUp)", "Database operation failed", e)
                }
            }
        }
    }


    @Suppress("UNCHECKED_CAST")
    class Factory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
        //        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserviewModel(userRepository) as T
        }
    }

}