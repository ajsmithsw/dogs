package com.example.dogs.ui.list

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.data.model.Breed
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BreedsListRouteTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val viewModel: BreedsListViewModel = mockk()

    @Before
    fun setUp() {
        every { viewModel.uiState.value } returns BreedsListUiState.Loading
    }

    @Test
    fun testBreedsListRoute() {
        val onClickEvents: MutableList<Breed> = mutableListOf()
        every { viewModel.handleEvent(BreedsListUiEvent.GetDogBreeds) } just Runs

        composeTestRule.setContent {
            BreedsListRoute(
                viewModel,
                onBreedClick = {
                    onClickEvents.add(it)
                }
            )
        }

        verify { viewModel.handleEvent(BreedsListUiEvent.GetDogBreeds) }
    }

}