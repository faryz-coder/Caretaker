package com.bmh.caretaker.broadcast

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.util.Log
import com.bmh.caretaker.model.ConstantNotification
import com.bmh.caretaker.model.Reminder

class NotificationManager {

    @SuppressLint("ScheduleExactAlarm")
    fun createNotification(context: Context, requestCode: Int, label: String, hour: Int, minute: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, ReminderReceiver::class.java)

        alarmIntent.putExtra(ConstantNotification.NOTIFICATION_ID, 1)
        alarmIntent.putExtra(ConstantNotification.REQUEST_CODE, requestCode)
        alarmIntent.putExtra(ConstantNotification.MESSAGE, label)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            alarmIntent,
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR_OF_DAY, hour)
        calendar.add(Calendar.MINUTE, minute)
        calendar.add(Calendar.SECOND, 0)

        // Subtract 30 days
        calendar.add(Calendar.DAY_OF_MONTH, -30)

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
        Log.d("MainViewModel", "createNotification")
    }

    fun removeNotification(context: Context, requestCode: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, ReminderReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(
            context, requestCode, alarmIntent, PendingIntent.FLAG_MUTABLE
        )

        // Cancel the alarm
        alarmManager.cancel(pendingIntent)
    }
}