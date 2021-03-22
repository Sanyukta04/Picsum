package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.util.DATABASE_NAME
import com.example.myapplication.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
                .build()
    }
    @Provides
    @Singleton
    fun provideImageDao(appDatabase: AppDatabase)= appDatabase.imageDao

}