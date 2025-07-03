package com.example.dogs.ui.list

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
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

@OptIn(ExperimentalSharedTransitionApi::class)
class BreedsListRouteTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel: BreedsListViewModel = mockk()

    @Before
    fun setUp() {
        every { viewModel.uiState.value } returns BreedsListUiState.Loading
    }

    @Test
    fun testBreedsListRoute() {
        val onClickEvents: MutableList<Breed> = mutableListOf()
        every { viewModel.handleEvent(BreedsListUiEvent.GetDogBreeds) } just Runs

        composeTestRule.setContent {
            SharedTransitionLayout {
                AnimatedContent(true, label = "testContent") { _ ->
                    BreedsListRoute(
                        this@SharedTransitionLayout,
                        this@AnimatedContent,
                        viewModel,
                        onBreedClick = {
                            onClickEvents.add(it)
                        }
                    )
                }
            }
        }

        verify { viewModel.handleEvent(BreedsListUiEvent.GetDogBreeds) }
    }

}