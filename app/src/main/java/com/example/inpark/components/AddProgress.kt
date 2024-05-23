package com.example.inpark.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.inpark.viewModels.AuthViewModel

@Composable
fun AddProgress(authViewModel: AuthViewModel){
    val isLoading = authViewModel.loading.value

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xffa0f000))
        }
    }
}