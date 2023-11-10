package com.example.loginsamplecompose

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

const val PASS_TEXT_FIELD_TAG = "passTextFieldTag"
const val PASS_REVEAL_ICON_TAG = "revealButtonTag"

@Composable
fun PassTextField(value: String, onValueChanged: (String) -> Unit) {
    var passVisible by remember { mutableStateOf(false) }
    val contentDescription = stringResource(id = R.string.pass_field)
    TextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = "Password") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions { },
        visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconToggleButton(
                checked = passVisible,
                onCheckedChange = { passVisible = it },
                modifier = Modifier.testTag(PASS_REVEAL_ICON_TAG)
            ) {
                Crossfade(
                    targetState = passVisible,
                    animationSpec = tween(durationMillis = 2000)
                ) { visible ->
                    if (visible) {
                        Icon(
                            imageVector = Icons.Default.VisibilityOff,
                            contentDescription = stringResource(id = R.string.hide_password)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = stringResource(id = R.string.reveal_password)
                        )
                    }

                }

            }
        },
        modifier = Modifier
            .testTag(PASS_TEXT_FIELD_TAG)
            .semantics { this.contentDescription = contentDescription }
    )
}