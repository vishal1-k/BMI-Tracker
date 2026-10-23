package com.vishal.bmitrackingapp.presentation.auth.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.vishal.bmitrackingapp.navigation.Screens

@Composable

fun ForgotPassword(
    onForgotPasswordChange:()-> Unit,
navController: NavController
) {

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        TextButton(
            onClick = onForgotPasswordChange,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text("Forgot Password?")
        }
    }

}