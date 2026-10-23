package com.vishal.bmitrackingapp.domain.error

sealed class AuthError : Exception(){
    data object EmailAlreadyInUse: AuthError()

    data object InvalidEmail : AuthError()

    data object WeakPassword : AuthError()

    data object InvalidCredentials : AuthError()

    data object NetworkError : AuthError()

    data object  Unknown : AuthError()
    data object UserNotFound : AuthError()
    data object WrongPassword : AuthError()

}