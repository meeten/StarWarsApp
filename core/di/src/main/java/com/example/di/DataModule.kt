package com.example.di

import com.example.data.network.CharactersRepositoryImpl
import com.example.data.network.network.ApiService
import com.example.domain.repository.CharactersRepository
import com.example.data.network.network.ApiFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
interface DataModule {

    @Binds
    fun bindsCharactersRepository(
        impl: CharactersRepositoryImpl
    ): CharactersRepository

    companion object {
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @Provides
        fun provideCoroutineScope(): CoroutineScope {
            return CoroutineScope(Dispatchers.Default)
        }
    }
}