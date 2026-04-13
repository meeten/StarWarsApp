package com.example.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.mapToScreenState
import com.example.domain.usecase.LoadCharactersUseCase
import com.example.domain.usecase.LoadNextCharactersUseCase
import com.example.domain.usecase.SearchCharactersByNameUseCase
import com.example.extension.toUserFriendlyMessage
import com.example.home.model.HomeErrorEvent
import com.example.home.model.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class HomeViewModel @Inject constructor(
    loadCharactersUseCase: LoadCharactersUseCase,
    searchCharactersByNameUseCase: SearchCharactersByNameUseCase,
    private val loadNextCharactersUseCase: LoadNextCharactersUseCase,
) : ViewModel() {

    private val isLoadingNextFlow = MutableStateFlow(false)
    private val queryFlow = MutableStateFlow("")

    private val _errorEvents = Channel<HomeErrorEvent>()
    val errorEvents = _errorEvents.receiveAsFlow()

    val uiState = queryFlow
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            Log.d("HomeViewModel", query)
            if (query.isBlank()) {
                loadCharactersUseCase()
            } else {
                searchCharactersByNameUseCase(query)
            }
        }
        .mapToScreenState(
            onSuccess = { characters ->
                HomeScreenState.Characters(
                    items = characters,
                    isLoadingNext = isLoadingNextFlow.value
                )
            },
            onError = { throwable ->
                HomeScreenState.Error(throwable)
            }
        ).onStart {
            emit(HomeScreenState.Loading)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeScreenState.Loading
        )


    init {
        loadNextData()
    }

    fun loadNextData() {
        if (isLoadingNextFlow.value) return

        viewModelScope.launch {
            isLoadingNextFlow.value = true
            try {
                loadNextCharactersUseCase()
            } catch (e: Exception) {
                sendErrorMessage(e)
            } finally {
                isLoadingNextFlow.value = false
            }
        }
    }

    fun search(query: String) {
        Log.d("HomeViewModel", "sear")
        queryFlow.value = query
    }

    fun sendErrorMessage(
        throwable: Throwable
    ) {
        viewModelScope.launch {
            val message = throwable.toUserFriendlyMessage()
            _errorEvents.send(HomeErrorEvent.ShowError(message))
        }
    }
}