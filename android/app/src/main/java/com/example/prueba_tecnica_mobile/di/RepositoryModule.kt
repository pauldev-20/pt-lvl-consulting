package com.example.prueba_tecnica_mobile.di

import com.example.prueba_tecnica_mobile.repository.ProjectRepository
import com.example.prueba_tecnica_mobile.repository.ProjectRepositoryImp
import com.example.prueba_tecnica_mobile.repository.UserRepository
import com.example.prueba_tecnica_mobile.repository.UserRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun userRepository(repo: UserRepositoryImp): UserRepository

    @Singleton
    @Binds
    abstract fun projectRepository(repo: ProjectRepositoryImp): ProjectRepository
}