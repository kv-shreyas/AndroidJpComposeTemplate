package com.example.jetpackComposeTemplate.data.local.di

import android.content.Context
import com.example.jetpackComposeTemplate.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }


    @Provides
    fun provideInstallationDao(database: AppDatabase) = database.installationDao()

}