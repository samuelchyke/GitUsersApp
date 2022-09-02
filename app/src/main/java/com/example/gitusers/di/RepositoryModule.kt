package com.example.gitusers.di

import com.example.gitusers.repositories.GitUsersRepository
import com.example.gitusers.repositories.GitUsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideNetworkRepository( gitUsersRepositoryImpl: GitUsersRepositoryImpl): GitUsersRepository

}