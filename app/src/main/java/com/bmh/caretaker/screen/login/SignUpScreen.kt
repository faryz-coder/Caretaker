package com.bmh.caretaker.screen.login

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bmh.caretaker.R

@Composable
fun SignUpScreen(
    viewModel: LoginViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.size(200.dp, 200.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(R.drawable.app_logo), contentDescription = "App Logo")
            }
        }

        var email by rememberSaveable {
            mutableStateOf("")
        }
        var password by rememberSaveable {
            mutableStateOf("")
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            NormalTextField(email = email, onValueChange = { email = it })
            PasswordField(
                label = "Enter Password",
                password = password,
                onValueChange = { password = it })
            Column(
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                PasswordField(
                    label = "Confirm Password",
                    password = password,
                    onValueChange = { password = it })
                NormalElevatedButton(title = "SUBMIT", onClick = {
                    viewModel.navHostController.popBackStack()
                })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Already registered? ", fontSize = 12.sp, color = Color.DarkGray)
                Text(
                    modifier = Modifier.clickable {
                        viewModel.navHostController.popBackStack()
                    },
                    text = "CLICK HERE.",
                    fontSize = 12.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignUp() {
    SignUpScreen(LoginViewModel())
}