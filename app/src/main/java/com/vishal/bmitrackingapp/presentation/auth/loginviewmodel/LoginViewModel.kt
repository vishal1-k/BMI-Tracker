package com.vishal.bmitrackingapp.presentation.auth.loginviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.bmitrackingapp.domain.repository.AuthRepository
import com.vishal.bmitrackingapp.domain.validator.AuthValidator
import com.vishal.bmitrackingapp.domain.error.AuthError
import com.vishal.bmitrackingapp.presentation.auth.loginuistate.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authValidator: AuthValidator
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()


    fun login(
        email: String,
        password: String
    ) {

        // Email validation
        val emailError =
            authValidator.validateEmail(email)

        if (emailError != null) {

            _uiState.value = LoginUiState(
                errorMessage = emailError
            )

            return
        }


        // Password validation
        val passwordError =
            authValidator.validatePassword(password)

        if (passwordError != null) {

            _uiState.value = LoginUiState(
                errorMessage = passwordError
            )

            return
        }


        // Firebase login
        viewModelScope.launch {

            _uiState.value = LoginUiState(
                isLoading = true
            )

            val result = authRepository.login(
                email = email,
                password = password
            )

            result
                .onSuccess {

                    _uiState.value = LoginUiState(
                        isSuccess = true
                    )
                }
                .onFailure { error ->

                    _uiState.value = LoginUiState(
                        errorMessage =
                            getErrorMessage(error)
                    )
                }
        }
    }


    private fun getErrorMessage(
        error: Throwable
    ): String {

        return when (error) {

            AuthError.InvalidCredentials ->
                "Invalid email or password."

            AuthError.InvalidEmail ->
                "Please enter a valid email."

            AuthError.NetworkError ->
                "Please check your internet connection."

            else ->
                "Something went wrong."
        }
    }
}