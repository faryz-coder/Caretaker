package com.bmh.caretaker.screen

sealed class Screen(val route: String) {

    // LOGIN ROUTE
    data object SignIn: Screen(route = "sign_in_screen")
    data object SignUp: Screen(route = "sign_up_screen")

    // HOME/MAIN ROUTE
    data object Home: Screen(route = "home_screen")
}