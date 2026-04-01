package com.example.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.home.model.HomeScreenState
import com.example.home.ui.CharactersContent
import com.example.loading.Loading
import com.example.topbar.CustomTopBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle(
        initialValue = HomeScreenState.Loading,
        minActiveState = Lifecycle.State.RESUMED
    )

    Scaffold(
        topBar = { CustomTopBar(title = stringResource(R.string.star_wars_characters)) }
    ) {
        Column(modifier.padding(top = it.calculateTopPadding())) {
            when (val currentState = uiState.value) {

                HomeScreenState.Loading -> {
                    Loading(modifier = Modifier.fillMaxSize())
                }

                is HomeScreenState.Characters -> {
                    CharactersContent(
                        characters = currentState.items,
                        isLoadingNext = currentState.isLoadingNext
                    ) {
                        viewModel.loadNextData()
                    }
                }

                is HomeScreenState.Error -> {
                    Text(text = currentState.message)
                }
            }
        }
    }
}