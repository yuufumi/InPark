package com.example.inpark.viewModels

import android.net.http.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inpark.data.api.FcmApi
import com.example.inpark.data.dto.NotificationBody
import com.example.inpark.data.dto.SendMessageDto
import com.example.inpark.states.ChatState
import kotlinx.coroutines.launch
import java.io.IOException

class ChatViewModel(private val fcmApi: FcmApi): ViewModel() {
    var state by mutableStateOf(ChatState())
        private set

    fun onRemoteTokenChange(message: String){
        state = state.copy(
            isEnteringToken = false
        )
    }

    fun sendMessage(isBroadcast:Boolean){
        viewModelScope.launch {
            val messageDto = SendMessageDto(
                to = if(isBroadcast) null else state.remoteToken,
                notification = NotificationBody(
                    title = "NewMessage!",
                    body = state.messageText
                )
            )

            try {
                if(isBroadcast) {
                    fcmApi.broadcast(messageDto)
                }else{
                    fcmApi.sendMessage(messageDto)
                }

                state = state.copy(
                    messageText = ""
                )
            } catch(e: HttpException) {
                e.printStackTrace()
            } catch(e: IOException) {
                e.printStackTrace()
            }
        }
    }
}