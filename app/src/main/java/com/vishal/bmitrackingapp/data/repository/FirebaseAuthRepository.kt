package com.vishal.bmitrackingapp.data.repository


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.vishal.bmitrackingapp.domain.error.AuthError
import com.vishal.bmitrackingapp.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository : AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()


    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            firebaseAuth
                .createUserWithEmailAndPassword(
                    email,
                    password
                )
                .await()

            val user = firebaseAuth.currentUser

            user?.updateProfile(
                userProfileChangeRequest {
                    displayName = name
                }
            )?.await()

            Result.success(Unit)

        } catch (exception: FirebaseAuthException) {
            Result.failure(
                mapFirebaseError(exception)
            )
        } catch (exception : Exception){
            Result.failure(
                AuthError.Unknown
            )
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            firebaseAuth
                .signInWithEmailAndPassword(
                    email,
                    password
                )
                .await()

            Result.success(Unit)

        } catch (exception: FirebaseAuthException) {
            Result.failure(
                mapFirebaseError(exception)
            )
        } catch (exception : Exception){
            Result.failure(
                AuthError.Unknown
            )

        }
    }

    override suspend fun resetPassword(
        email: String
    ): Result<Unit> {

        return try {

            firebaseAuth
                .sendPasswordResetEmail(email)
                .await()

            Result.success(Unit)

        } catch (exception: FirebaseAuthException) {
            Result.failure(
                mapFirebaseError(exception)
            )
        } catch (exception: Exception){
            Result.failure(
                AuthError.Unknown
            )
        }
    }

    override fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUserEmail(): String? {

        return firebaseAuth.currentUser?.email
    }

    override fun getCurrentUserName(): String? {

        return firebaseAuth.currentUser?.displayName
    }

    private fun mapFirebaseError(
        exception: FirebaseAuthException
    ): AuthError{
        return when (exception.errorCode){


            "ERROR_EMAIL_ALREADY_IN_USE" ->
                AuthError.EmailAlreadyInUse

            "ERROR_INVALID_EMAIL" ->
                AuthError.InvalidEmail

            "ERROR_WEAK_PASSWORD" ->
                AuthError.WeakPassword

            "ERROR_INVALID_CREDENTIAL" ->
                AuthError.InvalidCredentials

            else ->
                AuthError.Unknown

        }

    }
}