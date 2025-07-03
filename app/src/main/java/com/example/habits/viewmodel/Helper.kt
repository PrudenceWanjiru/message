package com.example.habits.viewmodel

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.habits.ReminderReceiver
import java.util.Calendar

fun scheduleDailyReminder(context: Context, habitName: String) {
    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra("habitName", habitName)
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        habitName.hashCode(),
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val triggerTime = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 9) // 9 AM
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        if (before(Calendar.getInstance())) add(Calendar.DATE, 1) // start from tomorrow
    }.timeInMillis

    alarmManager.setRepeating(
        AlarmManager.RTC_WAKEUP,
        triggerTime,
        AlarmManager.INTERVAL_DAY,
        pendingIntent
    )
}