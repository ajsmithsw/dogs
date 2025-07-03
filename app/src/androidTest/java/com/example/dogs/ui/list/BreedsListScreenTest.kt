package com.example.dogs.ui.list

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.ui.test.junit4.createComposeRule
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
    fun testBreedsListScreen() {
        val breeds = listOf("breed1", "breed2").map { Breed(it, emptyList()) }
        val state = BreedsListUiState.Success(breeds)
        val onClickEvents: MutableList<Breed> = mutableListOf()

        composeTestRule.setContent {
            SharedTransitionLayout {
                AnimatedContent(true, label = "testContent") { _ ->
                    BreedsListScreen(
                        state,
                        this@SharedTransitionLayout,
                        this@AnimatedContent,
                    ) {
                        onClickEvents.add(it)
                    }
                }
            }
        }

        composeTestRule.onNodeWithText("Breed1").assertExists()
        composeTestRule.onNodeWithText("Breed2").assertExists()
        composeTestRule.onNodeWithText("Breed1").performClick()
        composeTestRule.onNodeWithText("Breed2").performClick()

        assert(onClickEvents.size == 2)
        assert(onClickEvents[0].name == "breed1")
        assert(onClickEvents[1].name == "breed2")
    }
}