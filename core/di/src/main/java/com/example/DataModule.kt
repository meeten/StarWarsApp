package com.example

import android.app.Application
import com.example.data.network.ApiFactory
import com.example.data.network.ApiService
import com.example.data.repository.CharactersRepositoryImpl
import com.example.data.repository.DetailsRepositoryImpl
import com.example.db.CharactersDatabase
import com.example.db.LocalDataSourceImpl
import com.example.db.dao.CharactersDao
import com.example.domain.datasource.LocalDataSource
import com.example.domain.repository.CharactersRepository
import com.example.domain.repository.DetailsRepository
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

    @Binds
    fun bindsSpeciesRepository(
        impl: DetailsRepositoryImpl
    ): DetailsRepository

    @Binds
    @Singleton
    fun bindLocalDataSource(
        impl: LocalDataSourceImpl
    ): LocalDataSource

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

        @Provides
        fun provideCharactersDto(
            application: Application
        ): CharactersDao {
            return CharactersDatabase.getInstance(application).charactersDao
        }
    }
}