package com.vishal.bmitrackingapp.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.bmitrackingapp.domain.repository.BmiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class HistoryViewModel@Inject constructor(
    private val bmiRepository: BmiRepository
): ViewModel(){

    private val _uiState = MutableStateFlow(HistoryUiState())

    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init {
        loadHistory()
    }

    fun loadHistory(){

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )
            val result = bmiRepository.getBmiHistory()
            result
                .onSuccess { records ->

                    _uiState.value = HistoryUiState(
                        isLoading = false,
                        records = records
                    )
                }
                .onFailure { exception ->
                    _uiState.value = HistoryUiState(
                        isLoading = false,
                        error = exception.message
                    )
                }
        }
    }
}