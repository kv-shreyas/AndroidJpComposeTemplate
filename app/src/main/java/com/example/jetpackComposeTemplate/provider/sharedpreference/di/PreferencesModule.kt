package com.example.jetpackComposeTemplate.provider.sharedpreference.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import android.content.Context
import com.example.jetpackComposeTemplate.provider.sharedpreference.encrypted.EncryptedPreferencesManagerImpl
import com.example.jetpackComposeTemplate.provider.sharedpreference.encrypted.EncryptedPreferencesProvider
import com.example.jetpackComposeTemplate.provider.sharedpreference.simple.PreferencesManagerImpl
import com.example.jetpackComposeTemplate.provider.sharedpreference.simple.PreferencesProvider
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {
    @Provides
    fun providePreferencesManager(context: Context): PreferencesProvider {
        return PreferencesManagerImpl(context)
    }

    @Provides
    fun provideEncryptedPreferencesManager(context: Context): EncryptedPreferencesProvider {
        return EncryptedPreferencesManagerImpl(context)
    }
}
