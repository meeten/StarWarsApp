package com.example.data.network.dtos

import com.google.gson.annotations.SerializedName

data class SpecieDto(
    @SerializedName("name") val name: String,
    @SerializedName("language") val language: String
)