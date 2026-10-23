package com.vishal.bmitrackingapp.presentation.auth.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable

fun GoogleLogin(
    onGoogleLoginClick: () -> Unit
){
    OutlinedButton(
        onClick = onGoogleLoginClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = "G   Continue with Google",
            fontWeight = FontWeight.Medium
        )
    }

}