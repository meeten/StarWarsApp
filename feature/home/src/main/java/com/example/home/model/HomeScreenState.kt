package com.example.home.model

import com.example.domain.model.Character

sealed class HomeScreenState {

    object Loading : HomeScreenState()

    data class Characters(
        val items: List<Character>,
        val isLoadingNext: Boolean = false
    ) : HomeScreenState()

    data class Error(val message: String) : HomeScreenState()
}