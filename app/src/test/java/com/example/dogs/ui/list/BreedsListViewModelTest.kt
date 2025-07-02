package com.example.dogs.ui.list

import com.example.data.model.Breed
import com.example.data.repository.DogsRepository
import com.example.dogs.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BreedsListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val dogsRepository = mockk<DogsRepository>()
    private lateinit var viewModel: BreedsListViewModel

    @Before
    fun setup() {
        viewModel = BreedsListViewModel(dogsRepository)

        assert(viewModel.uiState.value is BreedsListUiState.Loading)
    }

    @Test
    fun `when getDogBreeds event is handled, then uiState is updated with success state`() = runTest {
        val breeds = listOf(
            Breed("breed1", listOf("subbreed1", "subbreed2")),
            Breed("breed2", listOf("subbreed3", "subbreed4"))
        )

        coEvery { dogsRepository.getDogBreeds() } returns breeds

        viewModel.handleEvent(BreedsListUiEvent.GetDogBreeds)

        assert(viewModel.uiState.value is BreedsListUiState.Success)
        val breedsResult = (viewModel.uiState.value as BreedsListUiState.Success).breeds
        assert(breedsResult.isNotEmpty())
    }

    @Test
    fun `when getDogBreeds event is handled, then uiState is updated with error state`() = runTest {
        coEvery { dogsRepository.getDogBreeds() } throws Exception("Failed to fetch dog breeds")

        viewModel.handleEvent(BreedsListUiEvent.GetDogBreeds)

        assert(viewModel.uiState.value is BreedsListUiState.Error)
    }
}