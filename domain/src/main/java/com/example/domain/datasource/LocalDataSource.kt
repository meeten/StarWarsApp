package com.example.domain.datasource

import com.example.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getAllCharacters(): List<Character>

    fun observerCharacters(): Flow<List<Character>>

    suspend fun getCharacterByName(name: String): Character

    suspend fun searchCharactersByName(name: String): List<Character>

    suspend fun getNextPageUrl(): String?

    suspend fun saveNextPageUrl(nextPageUrl: String?)

    suspend fun saveCharacters(characters: List<Character>)
}