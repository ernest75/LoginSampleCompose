package com.example.loginsamplecompose

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun UserTextField(value: String, onValueChanged: (String) -> Unit) {
    val contentDescription = stringResource(id = R.string.user_field )
    TextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = "User") },
        placeholder = { Text(text = "Use a valid email") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.semantics {
            this.contentDescription = contentDescription
        }
    )
}