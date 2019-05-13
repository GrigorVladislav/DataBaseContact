package com.example.contactdatabase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build


object  Notification {
    private var NOTIFICATIONS_CHANNEL_NAME: String? = null
    private val CHANNEL_ID: String = "12345"


    fun makeNotification(context: Context,title:String,text:String,icon:Int,intent:Intent){
        var builder: Notification.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChanel(context)
            builder = Notification.Builder(context, NOTIFICATIONS_CHANNEL_NAME)
        }else{
            builder = Notification.Builder(context)
        }
        var pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_ONE_SHOT)

            builder.setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
            .setSmallIcon(icon)
            .setContentIntent(pendingIntent)
        val notification = builder.build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0,notification)
    }

    private fun createNotificationChanel(context: Context) {
        NOTIFICATIONS_CHANNEL_NAME = "chanel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, NOTIFICATIONS_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            val manager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}