package com.vishal.bmitrackingapp.presentation.history



import com.vishal.bmitrackingapp.domain.model.BmiRecord

data class HistoryUiState(
    val isLoading: Boolean = false,
    val records: List<BmiRecord> = emptyList(),
    val error: String? = null
)