package com.bmh.caretaker.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.weight(0.4f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        onClick = {},
                    ) {
                        Text(text = "1")
                    }
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        onClick = {},
                    ) {
                        Text(text = "2")
                    }
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        onClick = {},
                    ) {
                        Text(text = "3")
                    }
                }
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        onClick = {},
                    ) {
                        Text(text = "4")
                    }
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        onClick = {},
                    ) {
                        Text(text = "5")
                    }
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        onClick = {},
                    ) {
                        Text(text = "6")
                    }
                }
            }
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(viewModel = MainViewModel())
}