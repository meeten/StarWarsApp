package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.mapToScreenState
import com.example.domain.usecase.LoadCharactersUseCase
import com.example.domain.usecase.LoadNextCharactersUseCase
import com.example.domain.usecase.SearchCharactersByNameUseCase
import com.example.home.model.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
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

    val uiState = queryFlow
        .debounce(300)
        .flatMapLatest { query ->
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
                HomeScreenState.Error(
                    message = throwable.message ?: "Unknown error"
                )
            }
        ).onStart {
            emit(HomeScreenState.Loading)
        }


    init {
        loadNextData()
    }

    fun loadNextData() {
        if (isLoadingNextFlow.value) return

        viewModelScope.launch {
            isLoadingNextFlow.value = true
            try {
                loadNextCharactersUseCase()
            } finally {
                isLoadingNextFlow.value = false
            }
        }
    }

    fun search(query: String) {
        queryFlow.value = query
    }
}