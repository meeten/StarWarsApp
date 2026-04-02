package com.example.domain.usecase

import com.example.domain.model.Character
import com.example.domain.model.OperationResult
import com.example.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchCharactersByNameUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    operator fun invoke(query: String): Flow<OperationResult<List<Character>>> {
        return repository.searchCharactersByName(query)
    }
}