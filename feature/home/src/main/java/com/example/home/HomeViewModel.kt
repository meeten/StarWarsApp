package com.example.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.mapToScreenState
import com.example.domain.usecase.LoadCharactersUseCase
import com.example.domain.usecase.LoadNextCharactersUseCase
import com.example.home.model.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    loadCharactersUseCase: LoadCharactersUseCase,
    private val loadNextCharactersUseCase: LoadNextCharactersUseCase
) : ViewModel() {

    private val isLoadingNextFlow = MutableStateFlow(false)

    val uiState = loadCharactersUseCase()
        .mapToScreenState(
            onSuccess = { characters ->
                if (characters.isEmpty()) HomeScreenState.Loading
                else HomeScreenState.Characters(items = characters)
            },
            onError = { throwable ->
                HomeScreenState.Error(message = throwable.message ?: "Unknown error")
            }
        ).combine(isLoadingNextFlow) { state, isLoadingNext ->
            when (state) {
                is HomeScreenState.Characters -> {
                    state.copy(
                        items = state.items,
                        isLoadingNext = isLoadingNext
                    )
                }

                else -> state
            }
        }.onStart {
            emit(HomeScreenState.Loading)
        }

    fun loadNextData() {
        viewModelScope.launch {
            isLoadingNextFlow.value = true
            try {
                loadNextCharactersUseCase()
            } finally {
                isLoadingNextFlow.value = false
            }
        }
    }
}