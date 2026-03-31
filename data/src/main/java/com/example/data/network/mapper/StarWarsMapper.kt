package com.example.data.network.mapper

import com.example.domain.model.Character
import com.example.data.network.network.dtos.CharactersResponseDto
import javax.inject.Inject

class StarWarsMapper @Inject constructor() {

    fun mapResponseToCharacters(response: CharactersResponseDto): List<Character> {
        val charactersDto = response.charactersDto
        val result = mutableListOf<Character>()
        charactersDto.forEach { characterDto ->
            result.add(
                Character(
                    name = characterDto.name,
                    height = characterDto.height,
                    mass = characterDto.mass,
                    hairColor = characterDto.hairColor ?: "n/a",
                    eyeColor = characterDto.eyeColor ?: "n/a",
                    birthYear = characterDto.birthYear,
                    gender = characterDto.gender,
                    species = characterDto.species,
                    films = characterDto.films
                )
            )
        }
        return result
    }
}