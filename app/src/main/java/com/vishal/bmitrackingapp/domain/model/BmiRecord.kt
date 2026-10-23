package com.vishal.bmitrackingapp.domain.model



data class BmiRecord(
    val id: String = "",
    val userId: String = "",
    val gender: String = "",
    val weight: Double = 0.0,
    val height: Double = 0.0,
    val weightUnit: String = "",
    val heightUnit: String = "",
    val bmi: Double = 0.0,
    val category: String = "",
    val timestamp: Long = System.currentTimeMillis()
)