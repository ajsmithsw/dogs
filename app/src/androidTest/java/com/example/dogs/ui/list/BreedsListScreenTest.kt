package com.example.dogs.ui.list

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.data.model.Breed
import org.junit.Rule
import org.junit.Test

class BreedsListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Test
    fun testSuccessLayout() {
        val breeds = listOf("breed1", "breed2").map { Breed(it, emptyList()) }
        val state = BreedsListUiState.Success(breeds)
        val onClickEvents: MutableList<Breed> = mutableListOf()
        var retryClickEvents: Int = 0

        composeTestRule.setContent {
            SharedTransitionLayout {
                AnimatedContent(true, label = "testContent") { _ ->
                    BreedsListScreen(
                        state,
                        this@SharedTransitionLayout,
                        this@AnimatedContent,
                        onBreedClick = { onClickEvents.add(it) },
                        onRetryClick = { retryClickEvents++ }
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Breed1")
            .assertExists()
            .performClick()

        composeTestRule.onNodeWithText("Breed2")
            .assertExists()
            .performClick()

        assert(onClickEvents.size == 2)
        assert(onClickEvents[0].name == "breed1")
        assert(onClickEvents[1].name == "breed2")
    }

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Test
    fun testErrorLayout() {
        val state = BreedsListUiState.Error
        var retryClickEvents: Int = 0

        composeTestRule.setContent {
            SharedTransitionLayout {
                AnimatedContent(true, label = "testContent") { _ ->
                    BreedsListScreen(
                        state,
                        this@SharedTransitionLayout,
                        this@AnimatedContent,
                        onBreedClick = { },
                        onRetryClick = { retryClickEvents++ }
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("errorScreen").assertExists()
        composeTestRule.onNodeWithText("Retry").performClick()

        assert(retryClickEvents == 1)
    }


    @OptIn(ExperimentalSharedTransitionApi::class)
    @Test
    fun testLoadingLayout() {
        val state = BreedsListUiState.Loading

        composeTestRule.setContent {
            SharedTransitionLayout {
                AnimatedContent(true, label = "testContent") { _ ->
                    BreedsListScreen(
                        state,
                        this@SharedTransitionLayout,
                        this@AnimatedContent,
                        onBreedClick = { },
                        onRetryClick = { }
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("loadingScreen").assertExists()
    }


}