package com.example.domain.repository

import com.example.domain.model.OperationResult
import com.example.domain.model.Character
import kotlinx.coroutines.flow.StateFlow

interface CharactersRepository {

    val characters: StateFlow<OperationResult<List<Character>>>

    suspend fun loadNextCharacters()
}