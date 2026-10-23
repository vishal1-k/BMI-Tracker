package com.vishal.bmitrackingapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.vishal.bmitrackingapp.data.repository.FirebaseAuthRepository
import com.vishal.bmitrackingapp.data.repository.FirebaseBmiRepository
import com.vishal.bmitrackingapp.domain.repository.AuthRepository
import com.vishal.bmitrackingapp.domain.repository.BmiRepository
import com.vishal.bmitrackingapp.domain.validator.AuthValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return FirebaseAuthRepository()
    }

    @Provides
    @Singleton
    fun provideAuthValidator(): AuthValidator {
        return AuthValidator()
    }

    @Provides
    @Singleton
    fun provideBmiRepository(): BmiRepository {
        return FirebaseBmiRepository()
    }
}