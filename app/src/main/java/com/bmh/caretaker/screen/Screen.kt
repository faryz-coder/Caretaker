package com.bmh.caretaker.screen

sealed class Screen(val route: String) {

    // LOGIN ROUTE
    data object SignIn: Screen(route = "sign_in_screen")
    data object SignUp: Screen(route = "sign_up_screen")

    // HOME/MAIN ROUTE
    data object Home: Screen(route = "home_screen")
    data object DailyMonitoring: Screen(route = "daily_monitoring_screen")
    data object PatientInformationScreen: Screen(route = "patient_information_screen")
    data object AddPatientInfoScreen: Screen(route = "add_patient_info_screen")
    data object MedicalNotesScreen: Screen(route = "medical_notes_screen")
    data object AddMedicalNotesScreen: Screen(route = "add_medical_notes_screen")
    data object ReminderScreen: Screen(route = "reminder_screen")
    data object DietGuideScreen: Screen(route = "diet_guide_screen")
    data object GuideAndTipsScreen: Screen(route = "guide_and_tips_screen")
}