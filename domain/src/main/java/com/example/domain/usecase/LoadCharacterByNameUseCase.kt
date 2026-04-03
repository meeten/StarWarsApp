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
import javax.inject.Inject

class LoadCharacterByNameUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val detailsRepository: DetailsRepository
) {

    operator fun invoke(name: String): Flow<OperationResult<CharacterDetails>> {
        return charactersRepository.loadCharacterByName(name).map { result ->
            when (result) {
                is OperationResult.Success -> {
                    val character = result.data

                    val loadSpecies = safeLoad {
                        coroutineScope {
                            character.species.map { url ->
                                async {
                                    detailsRepository.loadSpecieByUrl(url)
                                }
                            }.awaitAll()
                        }
                    }

                    val loadFilms = safeLoad {
                        coroutineScope {
                            character.films.map { url ->
                                async {
                                    detailsRepository.loadFilmByUrl(url)
                                }
                            }.awaitAll()
                        }
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
        }.catch { throwable ->
            emit(OperationResult.Failure(throwable))
        }
    }
}

private suspend fun <T> safeLoad(block: suspend () -> List<T>): List<T> {
    return try {
        block()
    } catch (_: Exception) {
        emptyList()
    }
}