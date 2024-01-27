package com.bmh.caretaker.viewmodel

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.bmh.caretaker.broadcast.ReminderReceiver
import com.bmh.caretaker.model.ConstantNotification.MESSAGE
import com.bmh.caretaker.model.ConstantNotification.NOTIFICATION_ID
import com.bmh.caretaker.model.ConstantNotification.REQUEST_CODE
import com.bmh.caretaker.model.Notes
import com.bmh.caretaker.model.Reminder
import com.bmh.caretaker.utils.SharedPreferenceManager

class MainViewModel: ViewModel() {
    lateinit var navController: NavHostController
    lateinit var sharedPreferenceManager: SharedPreferenceManager

    var isPermissionGranted = false

    var selectedNotes: Notes? = null
}