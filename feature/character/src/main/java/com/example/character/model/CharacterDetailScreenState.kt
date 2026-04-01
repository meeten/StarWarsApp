package com.example.character.model

import com.example.domain.model.Character
import com.example.domain.model.Film
import com.example.domain.model.Specie

sealed class CharacterDetailScreenState {

    object Loading : CharacterDetailScreenState()

    data class Data(
        val character: Character,
        val species: List<Specie>,
        val films: List<Film>
    ) : CharacterDetailScreenState()

    data class Error(
        val message: String
    ) : CharacterDetailScreenState()
}