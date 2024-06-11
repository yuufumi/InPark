package com.example.inpark.services

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.inpark.MainActivity
import com.example.inpark.R
import com.google.android.gms.common.internal.ImagesContract.URL
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import okio.IOException

class PushNotificationService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        // Update server
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.data.size > 0) {
            Log.d("FCM", "Message Data payload: " + message.data)
        }
        if (message.notification != null) {
            sendNotification(
                message.notification!!.body, message.notification!!.title, message.notification!!
                    .imageUrl
            )
        }
    }

    private fun sendTokenToServer(token: String) {
        // Implement this method to send the token to your app server.
        Log.d("FCM", "Token sent to server: $token")
    }

    private fun sendNotification(messageBody: String?, title: String?, imgUrl: Uri?) {
    }
}