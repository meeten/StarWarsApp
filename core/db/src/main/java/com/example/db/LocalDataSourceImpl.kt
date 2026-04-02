package com.example.db

import com.example.db.dao.CharactersDao
import com.example.db.mapper.StarWarsMapperLocal
import com.example.domain.datasource.LocalDataSource
import com.example.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val charactersDao: CharactersDao,
    private val mapper: StarWarsMapperLocal
) : LocalDataSource {

    private val _characters =
        MutableStateFlow<List<Character>>(emptyList())

    override suspend fun getAllCharacters(): List<Character> {
        val entity = charactersDao.getAllCharacters()
        _characters.value = mapper.mapEntityToCharacters(entity)
        return _characters.value
    }

    override fun observerCharacters(): Flow<List<Character>> = _characters

    override suspend fun getCharacterByName(name: String): Character {
        val entity = charactersDao.getCharacterByName(name)

        return mapper.mapEntityToCharacter(
            entity
                ?: throw IllegalStateException("Failed to display character details for name: $name")
        )
    }

    override suspend fun searchCharactersByName(name: String): List<Character> {
        val entities = charactersDao.searchCharactersByName(name)
        return mapper.mapEntityToCharacters(entities)
    }

    override suspend fun getNextPageUrl(): String? {
        val entity = charactersDao.getNextPageUrl() ?: return null
        return mapper.mapEntityToNextPageUrlString(entity)
    }

    override suspend fun saveNextPageUrl(nextPageUrl: String?) {
        val entity = mapper.mapNextUrlToEntity(nextPageUrl)
        charactersDao.saveNextPageUrl(entity)
    }

    override suspend fun saveCharacters(characters: List<Character>) {
        val entities = mapper.mapCharactersToEntities(characters)
        charactersDao.saveCharacters(entities)
        _characters.value = characters
    }
}