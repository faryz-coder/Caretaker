package com.bmh.caretaker.screen

sealed class Screen(val route: String) {

    data object SignIn: Screen(route = "sign_in_screen")
}