package com.example.habits

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

class ReminderReceiver : BroadcastReceiver() {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(context: Context, intent: Intent) {
        val channelId = "habit_reminder_channel"
        val habitName = intent.getStringExtra("habitName") ?: "your habit"

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.timer) // Replace with your own icon
            .setContentTitle("Habit Reminder")
            .setContentText("Don't forget to do \"$habitName\" today!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

//        val manager = NotificationManagerCompat.from(context)
//        manager.notify(Random().nextInt(), notification)
    }
}