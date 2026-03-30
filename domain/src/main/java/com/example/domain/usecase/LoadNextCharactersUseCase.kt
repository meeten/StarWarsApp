package com.example.domain.usecase

import com.example.domain.repository.CharactersRepository
import javax.inject.Inject

class LoadNextCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend operator fun invoke() {
        repository.loadNextCharacters()
    }
}