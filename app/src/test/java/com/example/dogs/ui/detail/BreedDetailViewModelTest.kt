package com.example.dogs.ui.detail

import com.example.data.repository.DogsRepository
import com.example.dogs.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BreedDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: BreedDetailViewModel
    private val dogsRepository = mockk<DogsRepository>()

    @Before
    fun setup() {
        viewModel = BreedDetailViewModel(dogsRepository)

        assert(viewModel.uiState.value is BreedUiState.Loading)
    }

    @Test
    fun `when getImagesForBreed event is handled, then uiState is updated with success state`() = runTest {
        val breed = "breed1"
        val imageUrls = listOf("url1", "url2", "url3")
        coEvery { dogsRepository.getImagesForBreed(breed) } returns imageUrls

        viewModel.handleEvent(BreedUiEvent.GetImagesForBreed(breed))

        assert(viewModel.uiState.value is BreedUiState.Success)

        val successState = viewModel.uiState.value as BreedUiState.Success
        assert(successState.imageUrls.sorted() == imageUrls.sorted())
    }

    @Test
    fun `when getImagesForBreed event is handled, then uiState is updated with error state`() = runTest {
        val breed = "breed1"
        coEvery { dogsRepository.getImagesForBreed(breed) } throws Exception("Failed to fetch images")

        viewModel.handleEvent(BreedUiEvent.GetImagesForBreed(breed))

        assert(viewModel.uiState.value is BreedUiState.Error)
    }

}