package com.example.inpark.services

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        // Update server
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        //  respond to received messages
    }

    private fun sendTokenToServer(token: String) {
        // Implement this method to send the token to your app server.
        Log.d("FCM", "Token sent to server: $token")
    }
}