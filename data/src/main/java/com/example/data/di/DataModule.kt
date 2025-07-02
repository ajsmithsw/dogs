package com.example.data.di

import com.example.data.repository.DogsRepository
import com.example.data.repository.DogsRepositoryImpl
import com.example.data.service.DogsService
import com.example.data.service.DogsServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindDogsRepository(
        dogsRepositoryImpl: DogsRepositoryImpl
    ): DogsRepository

    @Binds
    abstract fun bindDogsService(
        dogsServiceImpl: DogsServiceImpl
    ): DogsService
}