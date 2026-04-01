package com.example.data.network.network

import com.example.data.network.network.dtos.CharactersResponseDto
import com.example.data.network.network.dtos.FilmDto
import com.example.data.network.network.dtos.SpecieDto
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("people/")
    suspend fun loadCharacters(): CharactersResponseDto

    @GET
    suspend fun loadCharacters(
        @Url fullUrl: String
    ): CharactersResponseDto

    @GET
    suspend fun loadSpecie(
        @Url fullUrl: String
    ): SpecieDto

    @GET
    suspend fun loadFilm(
        @Url fullUrl: String
    ): FilmDto
}