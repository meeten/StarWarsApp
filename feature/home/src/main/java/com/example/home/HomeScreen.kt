package com.example.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.home.model.HomeErrorEvent
import com.example.home.model.HomeScreenState
import com.example.home.ui.CharactersContent
import com.example.home.ui.CustomSearchBar
import com.example.loading.Loading
import com.example.topbar.CustomTopBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    onClickCharacter: (String) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.errorEvents.collect { homeErrorEvent ->
            when (homeErrorEvent) {
                is HomeErrorEvent.ShowError -> {
                    val message = context.getString(homeErrorEvent.message)
                    snackbarHostState.showSnackbar(
                        message = message,
                        withDismissAction = true
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = { CustomTopBar(title = stringResource(R.string.star_wars_characters)) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier
                .padding(top = it.calculateTopPadding())
                .padding(top = 20.dp)
        ) {
            CustomSearchBar { query ->
                viewModel.search(query)
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (val currentState = uiState.value) {

                HomeScreenState.Loading -> {
                    Loading(modifier = Modifier.fillMaxSize())
                }

                is HomeScreenState.Characters -> {
                    CharactersContent(
                        characters = currentState.items,
                        isLoadingNext = currentState.isLoadingNext,
                        loadNextData = { viewModel.loadNextData() },
                        onClickCharacter = onClickCharacter,
                    )
                }

                is HomeScreenState.Error -> {
                    viewModel.sendErrorMessage(currentState.throwable)
                }
            }
        }
    }
}