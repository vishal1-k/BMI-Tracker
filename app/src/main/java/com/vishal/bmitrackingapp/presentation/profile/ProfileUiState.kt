package com.vishal.bmitrackingapp.presentation.profile


data class ProfileUiState(
    val email: String = "",
    val displayName: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)