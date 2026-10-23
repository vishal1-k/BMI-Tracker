package com.vishal.bmitrackingapp.presentation.auth.register


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun NameField(
    name: String,
    onNameChange: (String) -> Unit
) {

    OutlinedTextField(
        value = name,

        onValueChange = {
            onNameChange(it)
        },

        modifier = Modifier.fillMaxWidth(),

        label = {
            Text("Full Name")
        },

        placeholder = {
            Text("Enter your name")
        },

        singleLine = true,

        shape = RoundedCornerShape(12.dp)
    )
}