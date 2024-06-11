package com.example.inpark.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext

@Composable
fun EnterTokenDialog(
    token: String,
    onTokenChange: (String)-> Unit
){
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current


}