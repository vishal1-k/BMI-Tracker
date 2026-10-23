package com.vishal.bmitrackingapp.presentation.auth.login

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable

fun RegisterText(
    onRegisterClick: () -> Unit,
){
    TextButton(
        onClick = onRegisterClick
    ) {
        Text("Don't have an account?  Register")
    }
}