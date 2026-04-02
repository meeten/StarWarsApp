package com.example.character

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.character.model.CharacterDetailScreenState
import com.example.common.mapToScreenState
import com.example.domain.usecase.LoadCharacterByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CharacterViewModel @Inject constructor(
    loadCharacterByNameUseCase: LoadCharacterByNameUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val uiState = savedStateHandle
        .getStateFlow(CHARACTER_NAME_KEY, "")
        .filter { it.isNotBlank() }
        .flatMapLatest {
            loadCharacterByNameUseCase(it)
                .mapToScreenState(
                    onSuccess = { characterDetails ->
                        CharacterDetailScreenState.Data(
                            character = characterDetails.character,
                            species = characterDetails.species,
                            films = characterDetails.films
                        )
                    },
                    onError = { throwable ->
                        CharacterDetailScreenState.Error(
                            message = throwable.message ?: "Unknown error"
                        )
                    }
                )
        }.onStart {
            emit(CharacterDetailScreenState.Loading)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CharacterDetailScreenState.Loading

        )

    fun setCharacterName(name: String) {
        savedStateHandle[CHARACTER_NAME_KEY] = name
    }

    private companion object {
        const val CHARACTER_NAME_KEY = "characterName"
    }
}