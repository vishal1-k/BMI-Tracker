package com.vishal.bmitrackingapp.presentation.auth.forgotpasswordscreen




import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vishal.bmitrackingapp.presentation.auth.ForgotPasswordViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: ForgotPasswordViewModel =
        hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()


    LaunchedEffect(uiState.isSuccess) {

        if (uiState.isSuccess) {

            // Login screen par wapas
            navController.popBackStack()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Forgot Password",
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Enter your email and we will send you a password reset link."
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )


        OutlinedTextField(

            value = uiState.email,

            onValueChange = {
                viewModel.onEmailChange(it)
            },

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Email")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),

            singleLine = true
        )


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        if (uiState.errorMessage != null) {

            Text(
                text = uiState.errorMessage ?: ""
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }


        Button(

            onClick = {
                viewModel.sendResetEmail()
            },

            modifier = Modifier.fillMaxWidth(),

            enabled = !uiState.isLoading
        ) {

            if (uiState.isLoading) {

                CircularProgressIndicator()

            } else {

                Text(
                    text = "Send Reset Email"
                )
            }
        }


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        TextButton(
            onClick = {
                navController.popBackStack()
            }
        ) {

            Text(
                text = "Back to Login"
            )
        }
    }
}