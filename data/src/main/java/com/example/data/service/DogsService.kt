package com.example.data.service

import com.example.data.model.DogBreedsResponse
import com.example.data.model.DogImagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsService {

    @GET("breeds/list/all")
    suspend fun getDogBreeds(): Response<DogBreedsResponse>

    @GET("breed/{breed}/images")
    suspend fun getImagesForBreed(
        @Path("breed") breed: String
    ): Response<DogImagesResponse>
}

