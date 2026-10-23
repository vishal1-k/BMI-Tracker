package com.vishal.bmitrackingapp.presentation.auth.register

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable

fun LoginText(
    onLoginClick: () -> Unit = {}
){

    TextButton(
        onClick = onLoginClick
    ) {
        Text("Already have an account?  Login")
    }
}