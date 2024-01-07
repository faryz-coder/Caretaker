package com.bmh.caretaker.screen.daily_monitoring

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.R
import com.bmh.caretaker.viewmodel.MainViewModel

@Composable
fun DailyMonitoringScreen(
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
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

        // Patient name
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Patients Name")
        }

        // Add New
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { /*TODO*/ }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = "Add New")
                    Icon(Icons.Rounded.AddCircle, contentDescription = "Add New")
                }
            }
        }

        Text(text = "Current State")
        var heartBeatProgress by remember {
            mutableStateOf("90")
        }

        ElevatedCard {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(modifier = Modifier.size(70.dp), painter = painterResource(R.drawable.heart_beat) , contentDescription = "Heart Beat" )
                Text(text = "Heart Rate", modifier = Modifier.width(80.dp), textAlign = TextAlign.Center)
                Card {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(10.dp)
                    ) {
                        Text(text = heartBeatProgress)
                    }
                }
                Text(text = "BPM")
            }
        }

        var bloodProgress by remember {
            mutableStateOf("90")
        }

        ElevatedCard {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(modifier = Modifier.size(70.dp), painter = painterResource(R.drawable.blood_pressure) , contentDescription = "Heart Beat" )
                Text(text = "Blood Pressure", modifier = Modifier.width(80.dp), textAlign = TextAlign.Center)
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Card {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(10.dp)
                        ) {
                            Text(text = bloodProgress)
                        }
                    }
                    Card {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(10.dp)
                        ) {
                            Text(text = bloodProgress)
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    Text(text = "SYS")
                    Text(text = "DIA")
                }
            }
        }

        var oxygenLevelProgress by remember {
            mutableStateOf("96")
        }
        // Oxygen Level
        ElevatedCard {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(modifier = Modifier.size(70.dp), painter = painterResource(R.drawable.oxygen) , contentDescription = "Heart Beat" )
                Text(text = "Oxygen Level", modifier = Modifier.width(80.dp), textAlign = TextAlign.Center)
                Card {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(10.dp)
                    ) {
                        Text(text = heartBeatProgress)
                    }
                }
                Text(text = "%    ")
            }
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