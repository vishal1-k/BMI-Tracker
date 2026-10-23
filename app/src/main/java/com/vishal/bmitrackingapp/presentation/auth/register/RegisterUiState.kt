package com.vishal.bmitrackingapp.presentation.auth.register

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isRegistered: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false,
)