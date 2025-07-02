package com.example.data.repository

import com.example.data.model.Breed
import com.example.data.service.DogsService
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor(
    private val dogsService: DogsService
) : DogsRepository {

    override suspend fun getDogBreeds(): List<Breed> {
        val response = dogsService.getDogBreeds()
        if (response.isSuccessful) {
            val breedsResponse = response.body()?.breeds ?: emptyMap()
            return breedsResponse.map { (breed, subBreeds) ->
                Breed(breed, subBreeds)
            }
        } else {
            throw Exception("Failed to fetch dog breeds")
        }
    }
}