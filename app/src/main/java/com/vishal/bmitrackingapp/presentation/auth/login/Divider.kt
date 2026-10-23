package com.vishal.bmitrackingapp.presentation.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable

fun Divider(){

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider()

        Text(
            text = "OR",
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.bodySmall
        )

        HorizontalDivider()
    }
}