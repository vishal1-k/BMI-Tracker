package com.vishal.bmitrackingapp.domain.repository

interface AuthRepository {

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<Unit>

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit>

    suspend fun resetPassword(
        email: String
    ): Result<Unit>

    fun isUserLoggedIn(): Boolean

    fun logout()

    fun getCurrentUserEmail(): String?

    fun getCurrentUserName(): String?

}