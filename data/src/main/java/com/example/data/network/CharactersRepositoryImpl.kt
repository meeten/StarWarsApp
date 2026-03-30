package com.example.data.network

import com.example.data.network.mapper.StarWarsMapper
import com.example.data.network.network.ApiService
import com.example.domain.model.OperationResult
import com.example.domain.repository.CharactersRepository
import com.example.domain.model.Character
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: StarWarsMapper,
    coroutineScope: CoroutineScope
) : CharactersRepository {

    private val nextDataNeededEvents = MutableSharedFlow<Unit>(replay = 1)

    private val charactersCache = mutableMapOf<String, Character>()
    private val nextFrom: String? = null
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

            val nextFrom = response.next
            val characters = mapper.mapResponseToCharacters(response)
            emit(characters)
        }
    }

    override val characters: StateFlow<OperationResult<List<Character>>> =
        loadedCharacters
            .map { OperationResult.Success(it) }
            .retry(3) {
                delay(RETRY_TIMEOUT_MILLS)
                true
            }
            .catch {
                OperationResult.Failure<Character>(it)
            }
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.Lazily,
                initialValue = OperationResult.Success(emptyList())
            )


    override suspend fun loadNextCharacters() {
        nextDataNeededEvents.emit(Unit)
    }

    private companion object {
        const val RETRY_TIMEOUT_MILLS = 5000L
    }
}