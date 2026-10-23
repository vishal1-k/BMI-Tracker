package com.vishal.bmitrackingapp.presentation.Splash



import androidx.lifecycle.ViewModel
import com.vishal.bmitrackingapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    fun isUserLoggedIn(): Boolean {

        return authRepository.isUserLoggedIn()
    }
}