package com.example.db.mapper

import com.example.db.model.CharacterEntity
import com.example.db.model.NextPageUrlEntity
import com.example.domain.model.Character
import javax.inject.Inject

class StarWarsMapperLocal @Inject constructor() {

    fun mapEntityToCharacters(entity: List<CharacterEntity>): List<Character> {
        val result = mutableListOf<Character>()
        entity.forEach { characterEntity ->
            result.add(
                mapEntityToCharacter(characterEntity)
            )
        }
        return result
    }

    fun mapEntityToCharacter(entity: CharacterEntity): Character {
        return Character(
            name = entity.name,
            height = entity.height,
            mass = entity.mass,
            hairColor = entity.hairColor,
            eyeColor = entity.eyeColor,
            birthYear = entity.birthYear,
            gender = entity.gender,
            species = entity.species,
            films = entity.films
        )
    }

    fun mapCharactersToEntities(characters: List<Character>): List<CharacterEntity> {
        val result = mutableListOf<CharacterEntity>()
        characters.forEach { character ->
            result.add(
                CharacterEntity(
                    name = character.name,
                    height = character.height,
                    mass = character.mass,
                    hairColor = character.hairColor,
                    eyeColor = character.eyeColor,
                    birthYear = character.birthYear,
                    gender = character.gender,
                    species = character.species,
                    films = character.films
                )
            )
        }
        return result
    }

    fun mapNextUrlToEntity(nextPageUrl: String?): NextPageUrlEntity {
        return NextPageUrlEntity(url = nextPageUrl)
    }

    fun mapEntityToNextPageUrlString(entity: NextPageUrlEntity): String? {
        return entity.url
    }
}