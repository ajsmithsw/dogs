package com.example.data.repository

import com.example.data.model.DogBreedsResponse
import com.example.data.model.DogImagesResponse
import com.example.data.service.DogsService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DogsRepositoryImplTest {

    private val dogsService = mockk<DogsService>()
    private lateinit var dogsRepository: DogsRepository

    @Before
    fun setup() {
        dogsRepository = DogsRepositoryImpl(dogsService)
    }

    @Test
    fun `getDogBreeds returns list of breeds`() = runTest {
        val responseData:  Map<String, List<String>> = mapOf(
            "breed1" to listOf("subbreed1", "subbreed2"),
            "breed2" to listOf("subbreed3", "subbreed4")
        )

        coEvery {
            dogsService.getDogBreeds()
        } returns Response.success(DogBreedsResponse(responseData, "success"))


        val result = dogsRepository.getDogBreeds()

        assert(result.isNotEmpty())
        assertEquals(responseData.keys.toList(), result.map { it.name })
        assertEquals(responseData.values.flatten(), result.flatMap { it.subBreeds })
    }

    @Test
    fun `getDogBreeds throws exception when response is not successful`() = runTest {
        coEvery {
            dogsService.getDogBreeds()
        } returns Response.error(400, mockk(relaxed = true))

        try {
            dogsRepository.getDogBreeds()
        } catch (e: Exception) {
            assertEquals("Failed to fetch dog breeds", e.message)
        }
    }

    @Test
    fun `getImagesForBreed returns list of image URLs`() = runTest {
        val breed = "breed1"
        val responseData = listOf("url1", "url2")

        coEvery {
            dogsService.getImagesForBreed(breed)
        } returns Response.success(DogImagesResponse(responseData, "success"))

        val result = dogsRepository.getImagesForBreed(breed)

        assertEquals(responseData, result)
    }

    @Test
    fun `getImagesForBreed throws exception when response is not successful`() = runTest {
        val breed = "breed1"
        coEvery {
            dogsService.getImagesForBreed(breed)
        } returns Response.error(400, mockk(relaxed = true))

        try {
            dogsRepository.getImagesForBreed(breed)
        } catch (e: Exception) {
            assertEquals("Failed to fetch images for breed", e.message)
        }
    }
}