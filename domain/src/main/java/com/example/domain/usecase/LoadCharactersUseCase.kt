package com.example.domain.usecase

import com.example.domain.model.OperationResult
import com.example.domain.repository.CharactersRepository
import com.example.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke(): Flow<OperationResult<List<Character>>> {
        return repository.characters
    }
}