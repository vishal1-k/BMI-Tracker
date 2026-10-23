package com.vishal.bmitrackingapp.presentation.Splash



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vishal.bmitrackingapp.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel =
        hiltViewModel()
) {

    LaunchedEffect(Unit) {

        delay(1500)


        val destination =
            if (viewModel.isUserLoggedIn()) {

                Screens.HomeScreen.route

            } else {

                Screens.LoginScreen.route
            }


        navController.navigate(destination) {

            popUpTo(Screens.SplashScreen.route) {

                inclusive = true
            }
        }
    }


    Box(

        modifier =
            Modifier.fillMaxSize(),

        contentAlignment =
            Alignment.Center

    ) {

        Text(
            text = "BMI Tracker"
        )
    }
}