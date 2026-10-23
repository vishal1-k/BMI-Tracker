package com.vishal.bmitrackingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vishal.bmitrackingapp.presentation.Splash.SplashScreen
import com.vishal.bmitrackingapp.presentation.addbmi.AddBmiScreen
import com.vishal.bmitrackingapp.presentation.auth.forgotpasswordscreen.ForgotPasswordScreen
import com.vishal.bmitrackingapp.presentation.auth.login.ForgotPassword
import com.vishal.bmitrackingapp.presentation.auth.login.LoginScreen
import com.vishal.bmitrackingapp.presentation.auth.register.RegisterScreen
import com.vishal.bmitrackingapp.presentation.history.HistoryScreen
import com.vishal.bmitrackingapp.presentation.home.HomeScreen
import com.vishal.bmitrackingapp.presentation.profile.ProfileScreen

@Composable

fun NavigationScreen(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ){

        composable(
            Screens.SplashScreen.route
        ) {
            SplashScreen(
                navController = navController
            )
        }


        composable(Screens.LoginScreen.route){
            LoginScreen(navController = navController )
        }

        composable(
            route = Screens.RegisterScreen.route
        ) {
            RegisterScreen(navController = navController)
        }

        composable(
            route = Screens.ForgotPasswordScreen.route
        ) {
            ForgotPasswordScreen(
                navController = navController
            )
        }

        composable(
            Screens.HomeScreen.route
        ) {
            HomeScreen(

                navController = navController
            )
        }

        composable(
            Screens.AddBmiScreen.route
        ) {
            AddBmiScreen(
                navController = navController
            )
        }

        composable(
            Screens.HistoryScreen.route
        ) {
            HistoryScreen(
                navController = navController
            )
        }

        composable(
            Screens.ProfileScreen.route
        ) {
            ProfileScreen(
                navController
            )
        }




    }
}