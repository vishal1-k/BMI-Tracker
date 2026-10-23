package com.vishal.bmitrackingapp.presentation.home



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vishal.bmitrackingapp.data.repository.FirebaseAuthRepository
import com.vishal.bmitrackingapp.domain.repository.AuthRepository
import com.vishal.bmitrackingapp.domain.repository.BmiRepository
import com.vishal.bmitrackingapp.presentation.home.homeviewmodel.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
   private val bmiRepository: BmiRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val  _uiState = MutableStateFlow(
        HomeUiState()
    )

    val uiState : StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadLatestBmi()
    }

    private fun loadLatestBmi(){
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error= null
            )

            val result = bmiRepository.getLatestBmi()

            result
                .onSuccess { record ->
                    if(record !=null){

                        _uiState.value = HomeUiState(
                            isLoading = false,
                            bmi = record.bmi,
                            bmiCategory = record.category,
                            weight = record.weight,
                            height = record.height,
                            lastUpdated = "Recently"
                        )
                    } else {
                        _uiState.value =
                            HomeUiState(
                                isLoading = false
                            )
                    }

                }
                result.onFailure { exception ->
                    _uiState.value = HomeUiState(
                        isLoading = false,
                        error = exception.message
                    )
                }
        }
    }

    fun refreshBmi(){
        loadLatestBmi()
    }

    fun logout(){
        authRepository.logout()
    }
}