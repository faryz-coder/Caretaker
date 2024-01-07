package com.bmh.caretaker.screen.login

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.bmh.caretaker.MainActivity

class LoginViewModel: ViewModel() {
    lateinit var navHostController: NavHostController

    fun moveToMain() {
        val intent = Intent(navHostController.context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        navHostController.context.startActivity(intent)
    }
}