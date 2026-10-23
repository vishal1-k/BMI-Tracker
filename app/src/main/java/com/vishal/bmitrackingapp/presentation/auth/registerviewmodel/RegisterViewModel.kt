package com.vishal.bmitrackingapp.presentation.auth.registerviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.bmitrackingapp.domain.repository.AuthRepository
import com.vishal.bmitrackingapp.domain.error.AuthError
import com.vishal.bmitrackingapp.domain.validator.AuthValidator
import com.vishal.bmitrackingapp.presentation.auth.register.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject




@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authValidator: AuthValidator
): ViewModel(){
    private val _uiState = MutableStateFlow(RegisterUiState())

    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun register(
        name: String,
        email:String,
        password:String,
        confirmPassword:String
    ){


        val emailError = authValidator.validateEmail(email)

        if(emailError !=null){
            _uiState.value = RegisterUiState(
                name = name,
                errorMessage = emailError
            )
            return
        }

        val passwordError = authValidator.validatePassword(password)

        if(passwordError != null){
            _uiState.value = RegisterUiState(
                name = name,
                errorMessage = passwordError
            )
            return
        }

        val confirmPasswordError = authValidator.validateConfirmPassword(
            password,
            confirmPassword
        )


        if (confirmPasswordError != null) {
            _uiState.value = RegisterUiState(
                name = name,
                errorMessage = confirmPasswordError
            )
            return
        }



        viewModelScope.launch {
            _uiState.value = RegisterUiState(
                name = name,
                isLoading = true
            )

            val result = authRepository.register(
                name = name,
                email = email,
                password = password
            )
            result
                .onSuccess {
                    _uiState.value = RegisterUiState(
                        name = name,
                        isSuccess = true
                    )
                }
                .onFailure { exception ->
                    _uiState.value = RegisterUiState(
                        name = name,
                        errorMessage =
                            getErrorMessage(exception)

                    )
                }
        }
    }
}

private fun getErrorMessage(
    error: Throwable
): String {

    return when (error) {

        AuthError.EmailAlreadyInUse ->
            "An account already exists with this email."

        AuthError.InvalidEmail ->
            "Please enter a valid email."

        AuthError.WeakPassword ->
            "Password is too weak."

        AuthError.InvalidCredentials ->
            "Invalid email or password."

        AuthError.NetworkError ->
            "Please check your internet connection."

        AuthError.Unknown ->
            "Something went wrong."

        else ->
            "Something went wrong."
    }
}