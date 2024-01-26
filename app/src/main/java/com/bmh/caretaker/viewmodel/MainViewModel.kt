package com.bmh.caretaker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.bmh.caretaker.model.Notes
import com.bmh.caretaker.utils.SharedPreferenceManager

class MainViewModel: ViewModel() {
    lateinit var navController: NavHostController
    lateinit var sharedPreferenceManager: SharedPreferenceManager

    var isPermissionGranted = false

    var selectedNotes: Notes? = null
}