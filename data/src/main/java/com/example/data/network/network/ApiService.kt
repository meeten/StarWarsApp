package com.example.data.network.network

import com.example.data.network.network.dtos.CharactersResponseDto
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("people/")
    suspend fun loadCharacters(): CharactersResponseDto

    @GET
    suspend fun loadCharacters(
        @Url fullUrl: String
    ): CharactersResponseDto
}