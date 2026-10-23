package com.vishal.bmitrackingapp.presentation.auth.register

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable

fun EmailField(
    email: String,
    onEmailChange:(String) -> Unit
){




    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text("Email")
        },
        placeholder = {
            Text("Enter your email")
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp)
    )
}