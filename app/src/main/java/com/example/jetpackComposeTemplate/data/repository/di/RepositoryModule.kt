package com.example.jetpackComposeTemplate.data.repository.di

import com.example.jetpackComposeTemplate.data.remote.service.ApiService
import com.example.jetpackComposeTemplate.data.repository.UserRepository
import com.example.jetpackComposeTemplate.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(userService: ApiService): UserRepository {
        return UserRepositoryImpl(userService)
    }
}
