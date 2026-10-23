package com.vishal.bmitrackingapp.presentation.auth.forgetpassworduistate

data class ForgotPasswordUiState(
    val email: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)