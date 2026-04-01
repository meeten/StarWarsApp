package com.example.domain.repository

import com.example.domain.model.Film
import com.example.domain.model.Specie

interface DetailsRepository {

    suspend fun loadSpecieByUrl(url: String): Specie

    suspend fun loadFilmByUrl(url: String): Film
}