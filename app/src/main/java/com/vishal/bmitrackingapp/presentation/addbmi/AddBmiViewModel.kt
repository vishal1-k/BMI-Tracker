package com.vishal.bmitrackingapp.presentation.addbmi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.bmitrackingapp.domain.model.BmiRecord
import com.vishal.bmitrackingapp.domain.repository.BmiRepository
import com.vishal.bmitrackingapp.domain.usecase.CalculateBmiUseCase
import com.vishal.bmitrackingapp.domain.util.BmiUnitConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBmiViewModel @Inject constructor(
    private val bmiRepository: BmiRepository,
    private val calculateBmiUseCase: CalculateBmiUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddBmiUiState())

    val uiState: StateFlow<AddBmiUiState> = _uiState.asStateFlow()

    fun onWeightChange(value: String) {
        _uiState.update {
            it.copy(
                weight = value,
                bmi = null,
                category = "",
                errorMessage = null
            )
        }
    }

    fun onHeightChange(value: String) {
        _uiState.update {
            it.copy(
                height = value,
                bmi = null,
                category = "",
                errorMessage = null
            )
        }
    }

    fun onWeightUnitChange(unit: WeightUnit) {
        _uiState.update {
            it.copy(
                weightUnit = unit,
                bmi = null,
                category = "",
                errorMessage = null
            )
        }
    }

    fun onHeightUnitChange(unit: HeightUnit) {
        _uiState.update {
            it.copy(
                heightUnit = unit,
                bmi = null,
                category = "",
                errorMessage = null
            )
        }
    }

    fun onGenderChange(gender: Gender) {
        _uiState.update {
            it.copy(
                gender = gender,
                errorMessage = null
            )
        }
    }

    private fun validateInput(
        weight: Double?,
        height: Double?
    ): String? {

        if (weight == null) {
            return "Please enter a valid weight"
        }

        if (height == null) {
            return "Please enter a valid height"
        }

        if (weight <= 0) {
            return "Weight must be greater than 0"
        }

        if (height <= 0) {
            return "Height must be greater than 0"
        }

        return null
    }

    fun calculateBmi() {

        val state = _uiState.value

        val weightValue = state.weight.toDoubleOrNull()
        val heightValue = state.height.toDoubleOrNull()

        val validationError = validateInput(
            weight = weightValue,
            height = heightValue
        )

        if (validationError != null) {
            _uiState.update {
                it.copy(
                    errorMessage = validationError
                )
            }
            return
        }

        val weight = weightValue ?: return
        val height = heightValue ?: return

        val weightInKg = when (state.weightUnit) {

            WeightUnit.KG -> {
                BmiUnitConverter.kgToKg(weight)
            }

            WeightUnit.LB -> {
                BmiUnitConverter.lbToKg(weight)
            }
        }

        val heightInMeter = when (state.heightUnit) {

            HeightUnit.CM -> {
                BmiUnitConverter.cmToMeter(height)
            }

            HeightUnit.INCH -> {
                BmiUnitConverter.inchToMeter(height)
            }
        }

        val result = calculateBmiUseCase(weightInKg, heightInMeter)
        result
            .onSuccess { bmiResult ->
                _uiState.update {
                    it.copy(
                        bmi = bmiResult.bmi,
                        category = bmiResult.category,
                        errorMessage = null
                    )
                }
            }
            .onFailure { error ->
                _uiState.update {
                    it.copy(
                        errorMessage = error.message ?: "Failed to calculate BMI"
                    )
                }
            }


    }

    private fun getBmiCategory(bmi: Double): String {

        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 25.0 -> "Normal"
            bmi < 30.0 -> "Overweight"
            else -> "Obese"
        }
    }

    fun saveBmi() {

        val state = _uiState.value

        val weight = state.weight.toDoubleOrNull()
        val height = state.height.toDoubleOrNull()

        val validationError = validateInput(
            weight = weight,
            height = height
        )

        if (validationError != null) {
            _uiState.update {
                it.copy(
                    errorMessage = validationError
                )
            }
            return
        }

        if (state.bmi == null || state.category == null) {

            calculateBmi()

            val updatedState = _uiState.value

            if (
                updatedState.bmi == null ||
                updatedState.category == null
            ) {
                return
            }
        }

        val finalState = _uiState.value

        val finalWeight = finalState.weight.toDoubleOrNull()
            ?: return

        val finalHeight = finalState.height.toDoubleOrNull()
            ?: return

        val finalBmi = finalState.bmi
            ?: return

        val finalCategory = finalState.category
            ?: return

        val record = BmiRecord(
            gender = finalState.gender.name,
            weight = finalWeight,
            height = finalHeight,
            weightUnit = finalState.weightUnit.name,
            heightUnit = finalState.heightUnit.name,
            bmi = finalBmi,
            category = finalCategory
        )

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            bmiRepository
                .saveBmi(record)
                .onSuccess {

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSaved = true
                        )
                    }
                }
                .onFailure { error ->

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message
                                ?: "Failed to save BMI"
                        )
                    }
                }
        }
    }

    fun clearSavedState() {
        _uiState.update {
            it.copy(isSaved = false)
        }
    }
}