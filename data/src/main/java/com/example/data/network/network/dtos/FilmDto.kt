package com.example.data.network.network.dtos

import com.google.gson.annotations.SerializedName

data class FilmDto(
    @SerializedName("title") val title: String,
    @SerializedName("opening_crawl") val openingCrawl: String,
)
