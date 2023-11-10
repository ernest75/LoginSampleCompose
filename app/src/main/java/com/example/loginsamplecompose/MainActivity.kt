package com.example.loginsamplecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginsamplecompose.ui.theme.LoginSampleComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Login()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Preview
@Composable
fun Login() {
    Screen {
        var user by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var count by remember { mutableStateOf(0) }

        val loginEnabled = user.isNotEmpty() && password.isNotEmpty()

        val borderDp by animateDpAsState(targetValue = count.dp)

        Box(contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .wrapContentSize()
                    .background(Color.LightGray)
                    .border(borderDp, Color.Gray)
                    .padding(16.dp)
            ) {
                UserTextField(value = user, onValueChanged = { user = it})
                PassTextField(
                    value = password,
                    onValueChanged = { password = it })
                AnimatedContent(targetState = count, transitionSpec = {
                    slideInVertically { fullHeight -> fullHeight } + fadeIn() togetherWith
                            slideOutVertically { fullHeight -> -fullHeight } + fadeOut()
                }) {
                    Text(text = "Num of clicks: $it")
                }
                AnimatedVisibility(visible = loginEnabled) {
                    Button(
                        onClick = {
                            count++
                        }
                    ) {
                        Text(text = "Login", modifier = Modifier.semantics {
                            this.contentDescription = R.string.login.toString()
                        } )
                    }
                }

            }
        }

    }
}

fun validateLogin(user: String, password: String): String =
    when {
        !user.contains('@') -> "User must be a valid email"
        password.length < 5 -> "Password must have 5 characters"
        else -> ""
    }

@Composable
fun Screen(content: @Composable () -> Unit) {
    LoginSampleComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginSampleComposeTheme {
        Greeting("Android")
    }
}