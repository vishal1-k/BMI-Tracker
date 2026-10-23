package com.vishal.bmitrackingapp.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.vishal.bmitrackingapp.navigation.Screens
import com.vishal.bmitrackingapp.presentation.auth.loginviewmodel.LoginViewModel


@Composable
fun LoginScreen(

    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()

) {



    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()


    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }



    LaunchedEffect(uiState.isSuccess) {

        if (uiState.isSuccess) {

            navController.navigate(Screens.HomeScreen.route) {

                popUpTo(Screens.LoginScreen.route) {
                    inclusive = true
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Text(
            text = "BMI TRACKER",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Track your health. Understand your BMI.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(40.dp))


        Text(
            text = "Welcome Back 👋",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        EmailField(
            email = email,
            onEmailChange = {
                email = it

            }
        )

        Spacer(modifier = Modifier.height(16.dp))



        PasswordField(
            password= password,
            onPasswordChange = {
                password = it


            }
        )

        Spacer(modifier = Modifier.height(4.dp))



        ForgotPassword(
            onForgotPasswordChange = {
                navController.navigate(Screens.ForgotPasswordScreen.route)
            },
            navController = navController
        )

        Spacer(modifier = Modifier.height(12.dp))


        LoginButton(

            isLoading = uiState.isLoading,
            onLoginClick = {
                viewModel.login(
                    email = email,
                    password = password
                )
            }

        )

        Spacer(modifier = Modifier.height(24.dp))

        Divider()

        Spacer(modifier = Modifier.height(24.dp))


        GoogleLogin(
            onGoogleLoginClick = {

            }
        )

        Spacer(modifier = Modifier.height(20.dp))

       RegisterText(
           onRegisterClick = {
               navController.navigate(Screens.RegisterScreen.route)
           }
       )
    }
}

