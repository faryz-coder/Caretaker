package com.bmh.caretaker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmh.caretaker.navigation.LoginNavGraph
import com.bmh.caretaker.screen.login.LoginViewModel
import com.bmh.caretaker.ui.theme.CaretakerTheme
import com.bmh.caretaker.utils.AuthManager

class LoginActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setContent {
            val interactionSource = remember { MutableInteractionSource() }
            val focusManager = LocalFocusManager.current

            CaretakerTheme {
                // A surface container using the 'background' color from the theme
                navHostController = rememberNavController()
                loginViewModel.navHostController = navHostController
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(interactionSource = interactionSource, indication = null) {
                            focusManager.clearFocus()
                        }, color = MaterialTheme.colorScheme.background
                ) {
                    LoginNavGraph(loginViewModel = loginViewModel)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        AuthManager().isCurrentUser(
            onSuccess = {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            onFailed = {}
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CaretakerTheme {
        Greeting("Android")
    }
}