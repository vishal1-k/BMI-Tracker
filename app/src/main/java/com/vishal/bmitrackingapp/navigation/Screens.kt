package com.vishal.bmitrackingapp.navigation

sealed class Screens(
    val route: String
){

    object SplashScreen:
            Screens("Splash")
    object LoginScreen:
            Screens("LoginScreen")

    object RegisterScreen:
            Screens("RegisterScreen")

    object ForgotPasswordScreen:
            Screens("Forgot")

    object HomeScreen:
            Screens("HomeScreen")

    object AddBmiScreen:
            Screens("AddBmiScreen")

    object HistoryScreen:
            Screens("HistoryScreen")

    object ProfileScreen:
            Screens("ProfileScreen")
}
