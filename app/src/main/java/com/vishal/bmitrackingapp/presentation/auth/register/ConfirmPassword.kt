package com.vishal.bmitrackingapp.presentation.auth.register

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable

fun ConfirmPassword(
    confirmPassword: String,
    onConfirmPasswordChange:(String) ->Unit
){




    OutlinedTextField(
        value = confirmPassword,
        onValueChange = onConfirmPasswordChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text("Confirm Password")
        },
        placeholder = {
            Text("Re-enter your password")
        },
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )


}