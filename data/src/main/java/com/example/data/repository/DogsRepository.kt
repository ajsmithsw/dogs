package com.example.data.repository

import com.example.data.model.Breed

interface DogsRepository {

    suspend fun getDogBreeds(): List<Breed>
}