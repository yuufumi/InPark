package com.example.inpark.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inpark.data.model.User
import com.example.inpark.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel(private val authRepository: AuthRepository) :ViewModel() {
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user

    suspend fun setSignInValue(id: Int,
                               username: String,
                               nom: String,
                               prenom: String,
                               email: String,
                               mot_de_passe:  String,
                               num_telephone: String) {
        delay(2000)
        _user.value = User(id, username, nom, prenom, email, mot_de_passe, num_telephone)
    }

    class Factory(private val authRepository: AuthRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SignInViewModel(authRepository) as T
        }
    }
}