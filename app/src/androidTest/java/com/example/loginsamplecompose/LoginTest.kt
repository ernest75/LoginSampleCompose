package com.example.loginsamplecompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            Login()
        }
    }

        @Test
        fun tippingOnUserTextFieldNotShowsLogin(): Unit = with(composeTestRule) {
            onNodeWithContentDescription(appContext.getString(R.string.user_field)).performTextInput(
                "ererererer"
            )
            onNodeWithContentDescription(appContext.getString(R.string.login)).assertDoesNotExist()
        }

        @Test
        fun tippingOnUserTextAndPassFieldSShowsLogin(): Unit = with(composeTestRule) {
            onNodeWithContentDescription(appContext.getString(R.string.user_field)).performTextInput(
                "ererererer"
            )
            onNodeWithContentDescription(appContext.getString(R.string.pass_field)).performTextInput(
                "ererererer"
            )
            onNodeWithText("Login").assertExists()
        }
    }
