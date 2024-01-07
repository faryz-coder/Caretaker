package com.bmh.caretaker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bmh.caretaker.screen.Screen
import com.bmh.caretaker.screen.login.LoginViewModel
import com.bmh.caretaker.screen.login.SignInScreen
import com.bmh.caretaker.screen.login.SignUpScreen

@Composable
fun LoginNavGraph(
    loginViewModel: LoginViewModel
) {
    NavHost(navController = loginViewModel.navHostController, startDestination = Screen.SignIn.route) {
        composable(
            route = Screen.SignIn.route
        ) {
            SignInScreen(loginViewModel)
        }
        composable(
            route = Screen.SignUp.route
        ) {
            SignUpScreen(loginViewModel)
        }
    }
}