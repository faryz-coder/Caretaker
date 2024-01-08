package com.bmh.caretaker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bmh.caretaker.screen.Screen
import com.bmh.caretaker.screen.daily_monitoring.AddPatientInfoScreen
import com.bmh.caretaker.screen.daily_monitoring.DailyMonitoringScreen
import com.bmh.caretaker.screen.home.HomeScreen
import com.bmh.caretaker.screen.patient_info.PatientInformationScreen
import com.bmh.caretaker.viewmodel.MainViewModel

@Composable
fun MainNavGraph(
    mainViewModel: MainViewModel
) {
    NavHost(navController = mainViewModel.navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(mainViewModel)
        }
        composable(
            route = Screen.DailyMonitoring.route
        ) {
            DailyMonitoringScreen(mainViewModel)
        }
        composable(
            route = Screen.PatientInformationScreen.route
        ) {
            PatientInformationScreen(mainViewModel)
        }
        composable(
            route = Screen.AddPatientInfoScreen.route
        ) {
            AddPatientInfoScreen(mainViewModel)
        }
    }
}