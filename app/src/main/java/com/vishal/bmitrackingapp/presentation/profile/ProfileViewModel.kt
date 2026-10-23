package com.vishal.bmitrackingapp.presentation.profile

import androidx.lifecycle.ViewModel
import com.vishal.bmitrackingapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel

class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel(){


    private val _uiState =
        MutableStateFlow(ProfileUiState())

    val uiState: StateFlow<ProfileUiState> =
        _uiState.asStateFlow()


    init {
        loadUser()
    }

    private fun loadUser(){
        val email = authRepository.getCurrentUserEmail()
            ?:""

        val name = authRepository.getCurrentUserEmail()
            ?:"User"
        _uiState.value = ProfileUiState(
            email = email,
            displayName = name
        )

    }

    fun logout(){
        authRepository.logout()
    }



}
