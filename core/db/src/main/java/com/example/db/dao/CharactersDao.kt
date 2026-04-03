package com.example.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.db.model.CharacterEntity
import com.example.db.model.NextPageUrlEntity

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM pageUrl")
    suspend fun getNextPageUrl(): NextPageUrlEntity?

    @Query("SELECT * FROM characters WHERE name = :name")
    suspend fun getCharacterByName(name: String): CharacterEntity?

    @Insert
    suspend fun saveNextPageUrl(nextPageUrlEntity: NextPageUrlEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: List<CharacterEntity>)
}