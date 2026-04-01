package com.example.domain.model

data class CharacterDetails(
    val character: Character,
    val species: List<Specie>,
    val films: List<Film>
)