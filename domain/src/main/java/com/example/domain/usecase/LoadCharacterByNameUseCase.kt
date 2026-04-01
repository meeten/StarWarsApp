package com.example.domain.usecase

import com.example.domain.model.CharacterDetails
import com.example.domain.model.OperationResult
import com.example.domain.repository.CharactersRepository
import com.example.domain.repository.DetailsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class LoadCharacterByNameUseCase(
    private val charactersRepository: CharactersRepository,
    private val detailsRepository: DetailsRepository
) {

    operator fun invoke(name: String): Flow<OperationResult<CharacterDetails>> {
        return charactersRepository.loadCharacterByName(name).map { result ->
            when (result) {
                is OperationResult.Success -> {
                    val character = result.data

                    val loadSpecies = coroutineScope {
                        character.species.map { url ->
                            async {
                                detailsRepository.loadSpecieByUrl(url)
                            }
                        }.awaitAll()
                    }

                    val loadFilms = coroutineScope {
                        character.films.map { url ->
                            async {
                                detailsRepository.loadFilmByUrl(url)
                            }
                        }.awaitAll()
                    }

                    OperationResult.Success(
                        CharacterDetails(
                            character = result.data,
                            species = loadSpecies,
                            films = loadFilms
                        )
                    )
                }

                is OperationResult.Failure -> {
                    OperationResult.Failure(result.throwable)
                }
            }
        }.catch {
            emit(OperationResult.Failure(it))
        }
    }
}