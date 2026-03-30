package com.example.domain.model

data class Character(
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val species: List<String>,
    val films: List<String>
)