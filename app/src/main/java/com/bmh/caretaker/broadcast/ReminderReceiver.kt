package com.bmh.caretaker.broadcast

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bmh.caretaker.R
import com.bmh.caretaker.model.ConstantNotification.HOUR
import com.bmh.caretaker.model.ConstantNotification.MESSAGE
import com.bmh.caretaker.model.ConstantNotification.NOTIFICATION_ID
import com.bmh.caretaker.model.ConstantNotification.REQUEST_CODE

class ReminderReceiver : BroadcastReceiver() {
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationId = intent?.getIntExtra(NOTIFICATION_ID, 0)
        val requestCode = intent?.getIntExtra(REQUEST_CODE, 0)
        val message = intent?.getStringExtra(MESSAGE)
        val hour = intent?.getIntExtra(HOUR, 0)
        val minute = intent?.getIntExtra(MESSAGE, 0)

        if (notificationId != null && message != null && requestCode != null) {

            var builder = NotificationCompat.Builder(context, context.getString(R.string.app_name))
                .setSmallIcon(R.drawable.app_logo)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(message)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(message)
                )
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            with(NotificationManagerCompat.from(context)) {
                notify(0, builder.build())
                if (hour != null && minute != null) {
                    NotificationManager().createNotification(context, requestCode, message,  hour, minute)
                }
            }
        }
    }
}