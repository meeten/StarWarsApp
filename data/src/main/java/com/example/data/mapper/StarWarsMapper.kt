package com.example.data.mapper

import com.example.data.network.dtos.CharactersResponseDto
import com.example.data.network.dtos.SpecieDto
import com.example.data.network.network.dtos.FilmDto
import com.example.domain.model.Character
import com.example.domain.model.Film
import com.example.domain.model.Specie
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

    fun mapResponseToSpecie(response: SpecieDto): Specie {
        return Specie(
            name = response.name,
            language = response.language
        )
    }

    fun mapResponseToFilm(response: FilmDto): Film {
        return Film(
            title = response.title,
            openingCrawl = response.openingCrawl
        )
    }
}