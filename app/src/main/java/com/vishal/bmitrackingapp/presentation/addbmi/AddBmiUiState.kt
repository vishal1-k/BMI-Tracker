package com.vishal.bmitrackingapp.presentation.addbmi



data class AddBmiUiState(
    val gender: Gender = Gender.MALE,
    val weight: String = "",
    val height: String = "",
    val weightUnit: WeightUnit = WeightUnit.KG,
    val heightUnit: HeightUnit = HeightUnit.CM,
    val bmi: Double? = null,
    val category: String = "",
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val errorMessage: String? = null
)