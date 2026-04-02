package com.example.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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
