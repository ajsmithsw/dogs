package com.example.dogs.ui.list

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.data.model.Breed
import org.junit.Rule
import org.junit.Test

class BreedsListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testBreedsListScreen() {
        val breeds = listOf("breed1", "breed2").map { Breed(it, emptyList()) }
        val state = BreedsListUiState.Success(breeds)
        val onClickEvents: MutableList<Breed> = mutableListOf()

        composeTestRule.setContent {
            BreedsListScreen(state) {
                onClickEvents.add(it)
            }
        }

        composeTestRule.onNodeWithText("breed1").assertExists()
        composeTestRule.onNodeWithText("breed2").assertExists()
        composeTestRule.onNodeWithText("breed1").performClick()
        composeTestRule.onNodeWithText("breed2").performClick()

        assert(onClickEvents.size == 2)
        assert(onClickEvents[0].name == "breed1")
        assert(onClickEvents[1].name == "breed2")
    }
}