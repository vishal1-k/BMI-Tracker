package com.vishal.bmitrackingapp.presentation.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import com.vishal.bmitrackingapp.presentation.auth.registerviewmodel.RegisterViewModel

@Composable
fun RegisterScreen(

    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()

) {

    // viewmodel state observe kar ta hai




    var name by rememberSaveable {
        mutableStateOf("")
    }

    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()


    var email by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }


    //Registration successfull hone ke baad login screen par render krna

    LaunchedEffect(uiState.isSuccess) {
        if(uiState.isSuccess){
            navController.navigate(
                Screens.HomeScreen.route
            ){
                popUpTo(
                    Screens.RegisterScreen.route
                ){
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
            text = "Create Account",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Create your BMI Tracker account",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

       NameField(
           name = name,
           onNameChange = {
               name = it
           }

       )
        Spacer(modifier = Modifier.height(16.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

       ConfirmPassword(
           confirmPassword = confirmPassword,
           onConfirmPasswordChange = {
               confirmPassword = it
           }
       )
        Spacer(
            modifier = Modifier.height(16.dp)
        )


        uiState.errorMessage?.let { message ->
            Text(
                text = message
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

       RegisterButton(
           enabled =  !uiState.isLoading,
           onRegisterClick = {
               viewModel.register(
                   name = name,
                   email = email,
                   password = password,
                   confirmPassword = confirmPassword
               )
           }
       )

        if(uiState.isLoading){
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            CircularProgressIndicator()
        }

        Spacer(modifier = Modifier.height(16.dp))

        LoginText(
            onLoginClick = {
            navController.navigate(Screens.LoginScreen.route)
            }
        )

    }
}

