package com.bmh.caretaker.screen.login

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bmh.caretaker.MainActivity
import com.bmh.caretaker.R
import com.bmh.caretaker.screen.Screen
import com.bmh.caretaker.utils.AuthManager

@Composable
fun SignInScreen(
    viewModel: LoginViewModel
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(200.dp, 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.app_logo),
                    contentDescription = "App Logo"
                )
            }
        }

        var email by rememberSaveable {
            mutableStateOf("")
        }
        var password by rememberSaveable {
            mutableStateOf("")
        }

        var progress by remember {
            mutableStateOf(false)
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            NormalTextField(email = email, onValueChange = { email = it })
            PasswordField(
                label = "Enter password",
                password = password,
                onValueChange = { password = it })
            Column(
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(text = "Forgot Password", fontSize = 12.sp, color = Color.Gray)
                }
                NormalElevatedButton(title = "LOGIN", onClick = {
//                    viewModel.moveToMain()
                    progress = !progress
                    validateSignIn(
                        context,
                        email, password
                    ) {
                        progress = !progress
                    }
                })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Not yet registered? ", fontSize = 12.sp, color = Color.DarkGray)
                Text(
                    modifier = Modifier.clickable {
                        viewModel.navHostController.navigate(Screen.SignUp.route)
                    },
                    text = "CLICK HERE.",
                    fontSize = 12.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
            }
            if (progress) LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}

// Validate Form
fun validateSignIn(context: Context, email: String, password: String, onDone: () -> Unit) {
    AuthManager().signIn(
        email = email, password = password,
        onSuccess = {
            onDone.invoke()
            val intent = Intent(context, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        },
        onFailed = {
            onDone.invoke()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NormalElevatedButton(title: String = "", onClick: () -> Unit = {}) {
    ElevatedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(
            text = title
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NormalTextField(email: String = "", onValueChange: (String) -> Unit = {}) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = onValueChange,
        label = { Text(text = "Enter email") })
}


@Preview(showBackground = true)
@Composable
fun PasswordField(label: String = "", password: String = "", onValueChange: (String) -> Unit = {}) {
    var passwordHidden by rememberSaveable {
        mutableStateOf(true)
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(text = label) },
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility

                val description = if (passwordHidden) "Show password" else "Hide password"
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        })
}


@Preview(showBackground = true)
@Composable
fun PreviewSignIn() {
    SignInScreen(LoginViewModel())
}