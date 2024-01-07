package com.bmh.caretaker.screen.daily_monitoring

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.rounded.PermIdentity
import androidx.compose.material.icons.rounded.Person2
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.viewmodel.MainViewModel

@Composable
fun DailyMonitoringScreen(
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ElevatedCard {
                Box(
                    modifier = Modifier.size(150.dp)
                ) {
                    Icon(
                        Icons.Filled.PersonOutline,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
        var bloodProgress by remember {
            mutableFloatStateOf(0.3f)
        }
        // Blood Pressure
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Blood Pressure")
            ProgressBarForHealthLevel(bloodProgress)
        }
        var heartBeatProgress by remember {
            mutableFloatStateOf(0.8f)
        }
        // Heart Beat
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Heart Beat")
            ProgressBarForHealthLevel(heartBeatProgress)
        }
        var oxygenLevelProgress by remember {
            mutableFloatStateOf(0.5f)
        }
        // Oxygen Level
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Oxygen Level")
            ProgressBarForHealthLevel(oxygenLevelProgress)
        }

    }
}

@Composable
fun ProgressBarForHealthLevel(oxygenLevelProgress: Float) {
    LinearProgressIndicator(
        modifier = Modifier
            .height(20.dp)
            .fillMaxWidth(),
        progress = oxygenLevelProgress
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDailyMonitoringScreen() {
    DailyMonitoringScreen(MainViewModel())
}