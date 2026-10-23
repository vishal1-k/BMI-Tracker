package com.vishal.bmitrackingapp.presentation.home.homeviewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HealthValue(
    title: String,
    value: String
) {

    Column {

        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}