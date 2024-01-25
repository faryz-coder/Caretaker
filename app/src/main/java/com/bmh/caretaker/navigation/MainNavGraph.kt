package com.bmh.caretaker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bmh.caretaker.screen.Screen
import com.bmh.caretaker.screen.daily_monitoring.AddPatientInfoScreen
import com.bmh.caretaker.screen.daily_monitoring.DailyMonitoringScreen
import com.bmh.caretaker.screen.diet_guides.DietGuidesScreen
import com.bmh.caretaker.screen.guide_and_tips.GuideAndTipsScreen
import com.bmh.caretaker.screen.home.HomeScreen
import com.bmh.caretaker.screen.medical_notes.AddMedicalNotesScreen
import com.bmh.caretaker.screen.medical_notes.MedicalNotesScreen
import com.bmh.caretaker.screen.patient_info.PatientInformationScreen
import com.bmh.caretaker.screen.reminder.ReminderScreen
import com.bmh.caretaker.viewmodel.MainViewModel

@Composable
fun MainNavGraph(
    mainViewModel: MainViewModel,
    isHome: (Boolean) -> Unit
) {
    NavHost(navController = mainViewModel.navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(mainViewModel)
            isHome.invoke(true)
        }
        composable(
            route = Screen.DailyMonitoring.route
        ) {
            DailyMonitoringScreen(mainViewModel)
            isHome.invoke(false)
        }
        composable(
            route = Screen.PatientInformationScreen.route
        ) {
            PatientInformationScreen(mainViewModel)
            isHome.invoke(false)
        }
        composable(
            route = Screen.AddPatientInfoScreen.route
        ) {
            AddPatientInfoScreen(mainViewModel)
            isHome.invoke(false)
        }
        composable(
            route = Screen.MedicalNotesScreen.route
        ) {
            MedicalNotesScreen(mainViewModel)
            isHome.invoke(false)
        }
        composable(
            route = Screen.AddMedicalNotesScreen.route
        ) {
            AddMedicalNotesScreen(mainViewModel)
            isHome.invoke(false)
        }
        composable(
            route = Screen.ReminderScreen.route
        ) {
            ReminderScreen(mainViewModel)
            isHome.invoke(false)
        }
        composable(
            route = Screen.DietGuideScreen.route
        ) {
            DietGuidesScreen(mainViewModel)
            isHome.invoke(false)
        }
        composable(
            route = Screen.GuideAndTipsScreen.route
        ) {
            GuideAndTipsScreen(mainViewModel)
            isHome.invoke(false)
        }
    }
}