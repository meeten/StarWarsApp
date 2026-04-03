package com.example.data.network.dtos

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: String,
    @SerializedName("mass") val mass: String,
    @SerializedName("hair_color") val hairColor: String?,
    @SerializedName("eye_color") val eyeColor: String?,
    @SerializedName("birth_year") val birthYear: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("species") val species: List<String>,
    @SerializedName("films") val films: List<String>
)