package com.vishal.bmitrackingapp.presentation.auth.loginuistate


data class LoginUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)