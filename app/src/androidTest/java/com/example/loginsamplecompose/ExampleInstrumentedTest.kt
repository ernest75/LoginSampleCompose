package com.example.loginsamplecompose

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.platform.app.InstrumentationRegistry

import org.junit.Test

import org.junit.Rule

class ExampleInstrumentedTest {
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun sampleText(): Unit = with(composeTestRule) {
        val godBye = "Gooooodbye"
        setContent {
            var text by remember { mutableStateOf("Hello") }
            Button(onClick = {
                text = godBye
            }) {
                Text(text = text)
            }
        }

        onRoot().printToLog("Lord_ern")
        onRoot(useUnmergedTree = true).printToLog("Lord_ern")

        onNodeWithText("Hello").performClick()

        onNodeWithText(godBye).assertExists()
    }
}