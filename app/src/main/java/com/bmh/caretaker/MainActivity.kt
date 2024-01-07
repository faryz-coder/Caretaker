package com.bmh.caretaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bmh.caretaker.navigation.MainNavGraph
import com.bmh.caretaker.ui.theme.CaretakerTheme
import com.bmh.caretaker.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            val interactionSource = remember { MutableInteractionSource() }
            val focusManager = LocalFocusManager.current

            CaretakerTheme {
                navHostController = rememberNavController()
                mainViewModel.navController = navHostController
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(interactionSource = interactionSource, indication = null) {
                            focusManager.clearFocus()
                        }, color = MaterialTheme.colorScheme.background
                ) {
                    MainNavGraph(mainViewModel = mainViewModel)
                }
            }
        }
    }
}