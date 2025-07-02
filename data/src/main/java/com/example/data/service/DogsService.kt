package com.example.data.service

import com.example.data.model.DogBreedsResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogsService {

    @GET("breeds/list/all")
    suspend fun getDogBreeds(): Response<DogBreedsResponse>
}