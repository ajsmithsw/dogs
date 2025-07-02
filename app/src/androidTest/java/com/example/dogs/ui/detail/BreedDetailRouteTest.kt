package com.example.dogs.ui.detail

import androidx.compose.ui.test.junit4.createComposeRule
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BreedDetailRouteTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val viewModel: BreedDetailViewModel = mockk()

    @Before
    fun setUp() {
        every { viewModel.uiState.value } returns BreedUiState.Loading
    }

    @Test
    fun testBreedDetailRoute() {
        val breed = "breed1"

        every { viewModel.handleEvent(BreedUiEvent.GetImagesForBreed(breed)) } just Runs

        composeTestRule.setContent {
            BreedDetailRoute(
                breed,
                viewModel
            ) {}
        }

        verify { viewModel.handleEvent(BreedUiEvent.GetImagesForBreed(breed)) }
    }


}