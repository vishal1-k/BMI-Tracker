package com.vishal.bmitrackingapp.presentation.auth





import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.bmitrackingapp.domain.repository.AuthRepository
import com.vishal.bmitrackingapp.presentation.auth.forgetpassworduistate.ForgotPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(ForgotPasswordUiState())

    val uiState: StateFlow<ForgotPasswordUiState> =
        _uiState.asStateFlow()


    fun onEmailChange(value: String) {

        _uiState.value =
            _uiState.value.copy(
                email = value,
                errorMessage = null
            )
    }


    fun sendResetEmail() {

        val email =
            _uiState.value.email.trim()

        if (email.isBlank()) {

            _uiState.value =
                _uiState.value.copy(
                    errorMessage = "Please enter your email"
                )

            return
        }


        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    errorMessage = null
                )

            val result =
                authRepository.resetPassword(email)


            result
                .onSuccess {

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                }

                .onFailure { exception ->

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            errorMessage = exception.message
                        )
                }
        }
    }
}