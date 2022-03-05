package com.example.firebasetestapp.di


import com.example.firebasetestapp.auth.FirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseClient() : FirebaseRepository {
        return FirebaseRepository()
    }
}