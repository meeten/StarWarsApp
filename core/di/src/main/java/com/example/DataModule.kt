package com.example

import com.example.data.network.CharactersRepositoryImpl
import com.example.data.network.network.ApiService
import com.example.domain.repository.CharactersRepository
import com.example.data.network.network.ApiFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsCharactersRepository(
        impl: CharactersRepositoryImpl
    ): CharactersRepository

    companion object {
        @Provides
        @Singleton
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @Provides
        @Singleton
        fun provideCoroutineScope(): CoroutineScope {
            return CoroutineScope(Dispatchers.Default)
        }
    }
}