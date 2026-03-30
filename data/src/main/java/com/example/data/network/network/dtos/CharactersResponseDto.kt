package com.example.data.network.network.dtos

import com.google.gson.annotations.SerializedName

data class CharactersResponseDto(
    @SerializedName("next") val next: String?,
    @SerializedName("results") val charactersDto: List<CharacterDto>,
)