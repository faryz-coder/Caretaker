package com.bmh.caretaker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bmh.caretaker.screen.Screen
import com.bmh.caretaker.screen.login.SignInScreen

@Composable
fun LoginNavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screen.SignIn.route) {
        composable(
            route = Screen.SignIn.route
        ) {
            SignInScreen()
        }
    }
}