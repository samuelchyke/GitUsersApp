package com.example.gitusers.di

import android.content.Context
import androidx.room.Room
import com.example.gitusers.cache.GitUserDAO
import com.example.gitusers.cache.GitUserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun providesRoomDb(@ApplicationContext appContext: Context): GitUserDatabase =
        Room.databaseBuilder(
            appContext,
            GitUserDatabase::class.java,
            "user-db"
        ).build()

    @Provides
    @Singleton
    fun providesCocktailDAO(database: GitUserDatabase): GitUserDAO =
        database.gitUserDAO()

}

