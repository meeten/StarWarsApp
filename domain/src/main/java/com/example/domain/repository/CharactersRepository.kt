package com.example.domain.repository

import com.example.domain.model.OperationResult
import com.example.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface CharactersRepository {

    val characters: StateFlow<OperationResult<List<Character>>>

    suspend fun loadNextCharacters()

    fun searchCharactersByName(query: String): Flow<OperationResult<List<Character>>>

    fun loadCharacterByName(name: String): Flow<OperationResult<Character>>
}