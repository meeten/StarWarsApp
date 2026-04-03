package com.example.data.repository

import com.example.data.mapper.StarWarsMapper
import com.example.data.network.ApiService
import com.example.domain.model.Film
import com.example.domain.model.Specie
import com.example.domain.repository.DetailsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: StarWarsMapper
) : DetailsRepository {

    override suspend fun loadSpecieByUrl(url: String): Specie {
        val response = apiService.loadSpecie(url)
        return mapper.mapResponseToSpecie(response)
    }

    override suspend fun loadFilmByUrl(url: String): Film {
        val response = apiService.loadFilm(url)
        return mapper.mapResponseToFilm(response)
    }
}