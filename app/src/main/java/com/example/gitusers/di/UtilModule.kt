package com.example.gitusers.di

import com.example.gitusers.utils.HandleGitUserResponse
import com.example.gitusers.utils.HandleSearchResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UtilModule {

    @Provides
    fun provideHandleGitUserResponse() : HandleGitUserResponse = HandleGitUserResponse()

    @Provides
    fun provideHandleSearchResponse() : HandleSearchResponse = HandleSearchResponse()

}