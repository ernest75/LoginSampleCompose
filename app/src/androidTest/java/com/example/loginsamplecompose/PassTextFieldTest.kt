package com.example.loginsamplecompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PassTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var state by remember { mutableStateOf("") }
            PassTextField(value = state, onValueChanged = { state = it })
        }
    }

    @Test
    fun revealIconShowsPassword(): Unit =
        with(composeTestRule) {
            onNodeWithText("").performTextInput("pass")
            onNodeWithText("••••").assertExists()
            onNodeWithContentDescription(appContext.getString(R.string.reveal_password)).performClick()
            onNodeWithText("pass").assertExists()
        }

    @Test
    fun revealIconShowsPasswordWithTags(): Unit =
        with(composeTestRule) {
            onNodeWithTag(PASS_TEXT_FIELD_TAG).performTextInput("pass")
            onNodeWithTag(PASS_TEXT_FIELD_TAG).assertTextContains("••••")
            onNodeWithTag(PASS_REVEAL_ICON_TAG).performClick()
            onNodeWithTag(PASS_TEXT_FIELD_TAG).assertTextContains("pass")
        }

}