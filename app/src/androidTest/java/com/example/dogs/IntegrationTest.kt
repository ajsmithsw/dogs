package com.example.dogs

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class IntegrationTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun happyPathFlowTest() {
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag("breedsList").fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag("breedsList").assertIsDisplayed()

        composeRule.onNodeWithTag("listItem_0").performClick()

        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag("detailScreen").fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag("detailScreen").assertIsDisplayed()
    }
}