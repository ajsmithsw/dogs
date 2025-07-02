package com.example.data.di

import com.example.data.BASE_URL
import com.example.data.repository.DogsRepository
import com.example.data.repository.DogsRepositoryImpl
import com.example.data.service.DogsService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDogsRepository(
        dogsRepositoryImpl: DogsRepositoryImpl
    ): DogsRepository
}

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideDogsService(): DogsService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(DogsService::class.java)
    }
}