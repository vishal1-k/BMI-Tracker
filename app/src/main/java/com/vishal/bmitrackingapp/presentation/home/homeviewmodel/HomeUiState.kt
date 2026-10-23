package com.vishal.bmitrackingapp.presentation.home.homeviewmodel



data class HomeUiState(
    val isLoading: Boolean = false,
    val bmi: Double? = null,
    val bmiCategory: String = "",
    val lastUpdated: String = "",
    val error: String? = null,
    val weight: Double? = null,

    val height: Double? = null,
)