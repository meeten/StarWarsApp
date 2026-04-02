package com.example.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.character.extension.getFieldsInfo
import com.example.character.model.CharacterDetailScreenState
import com.example.character.ui.CharacterContent
import com.example.loading.Loading
import com.example.topbar.CustomTopBar
import com.example.topbar.NavigationBackIcon

@Composable
fun CharacterScreen(
    name: String,
    modifier: Modifier = Modifier,
    characterViewModel: CharacterViewModel = hiltViewModel(),
    onClickBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        characterViewModel.setCharacterName(name)
    }

    val uiState = characterViewModel.uiState.collectAsStateWithLifecycle(
        initialValue = CharacterDetailScreenState.Loading,
        minActiveState = Lifecycle.State.RESUMED
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopBar(
                title = name,
                navigationBackIcon = {
                    NavigationBackIcon(onClickBack = onClickBack)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            when (val currentState = uiState.value) {
                CharacterDetailScreenState.Loading -> {
                    Loading(modifier = Modifier.fillMaxSize())
                }

                is CharacterDetailScreenState.Data -> {
                    CharacterContent(
                        fieldsInfo = currentState.character.getFieldsInfo(),
                        species = currentState.species,
                        films = currentState.films
                    )
                }

                is CharacterDetailScreenState.Error -> {
                    Text(text = currentState.message)
                }
            }
        }
    }
}