package com.example.data.network.repository

import com.example.data.network.mapper.StarWarsMapper
import com.example.data.network.network.ApiService
import com.example.domain.model.Character
import com.example.domain.model.OperationResult
import com.example.domain.repository.CharactersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: StarWarsMapper,
    coroutineScope: CoroutineScope
) : CharactersRepository {

    private val nextDataNeededEvents = MutableSharedFlow<Unit>(replay = 1)

    private val charactersCache = mutableMapOf<String, Character>()
    private var nextFrom: String? = null
    private val loadedCharacters = flow {
        nextDataNeededEvents.emit(Unit)
        nextDataNeededEvents.collect {
            val startFrom = nextFrom
            if (startFrom == null && charactersCache.isNotEmpty()) {
                emit(charactersCache.values.toList())
                return@collect
            }

            val response = if (startFrom == null) {
                apiService.loadCharacters()
            } else {
                apiService.loadCharacters(fullUrl = startFrom)
            }

            nextFrom = response.next
            val characters = mapper.mapResponseToCharacters(response)
            characters.forEach { character ->
                charactersCache[character.name] = character
            }
            emit(characters)
        }
    }

    override val characters =
        loadedCharacters.map { OperationResult.Success(it) as OperationResult<List<Character>> }
            .retry(1) {
                delay(RETRY_TIMEOUT_MILLS)
                true
            }.catch { throwable ->
                emit(OperationResult.Failure<List<Character>>(throwable))
            }.stateIn(
                scope = coroutineScope,
                started = SharingStarted.Companion.Lazily,
                initialValue = OperationResult.Success(emptyList())
            )


    override suspend fun loadNextCharacters() {
        nextDataNeededEvents.emit(Unit)
    }

    override fun loadCharacterByName(name: String) = flow {
        emit(
            charactersCache[name]
                ?: throw IllegalStateException("Failed to display character details for name: $name")
        )
    }.map {
        OperationResult.Success(it) as OperationResult<Character>
    }.catch { throwable ->
        emit(OperationResult.Failure<Character>(throwable))
    }

    private companion object {
        const val RETRY_TIMEOUT_MILLS = 3000L
    }
}