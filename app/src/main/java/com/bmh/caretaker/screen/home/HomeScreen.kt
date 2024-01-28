package com.bmh.caretaker.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.R
import com.bmh.caretaker.model.Reminder
import com.bmh.caretaker.screen.Screen
import com.bmh.caretaker.utils.Utils
import com.bmh.caretaker.viewmodel.MainViewModel
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel
) {
    val reminders: MutableList<Reminder> = remember { mutableStateListOf() }
    try {
        viewModel.sharedPreferenceManager.getListReminder().let {
            reminders.clear()
            reminders.addAll(it)
        }

    } catch (e: Exception) {
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
//            modifier = Modifier.weight(0.7f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                    ) {
                        HomeItem(
                            label = "Patient Information",
                            onClick = {
                                viewModel.navController.navigate(Screen.PatientInformationScreen.route)
                            }, R.drawable.patient_info
                        )
                    }
                    Box(
                        modifier = Modifier.weight(1f),
                    ) {
                        HomeItem(
                            label = "Daily Monitoring",
                            onClick = { viewModel.navController.navigate(Screen.DailyMonitoring.route) },
                            R.drawable.daily_monitoring
                        )
                    }
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        HomeItem(
                            label = "Reminder",
                            onClick = { viewModel.navController.navigate(Screen.ReminderScreen.route) },
                            R.drawable.reminder
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                    ) {
                        HomeItem(
                            label = "Medical Notes",
                            onClick = { viewModel.navController.navigate(Screen.MedicalNotesScreen.route) },
                            R.drawable.medical_notes
                        )
                    }
                    Box(
                        modifier = Modifier.weight(1f),
                    ) {
                        HomeItem(
                            label = "Diet Guide",
                            onClick = { viewModel.navController.navigate(Screen.DietGuideScreen.route) },
                            R.drawable.diet
                        )
                    }
                    Box(
                        modifier = Modifier.weight(1f),
                    ) {
                        HomeItem(
                            label = "Guide and Tips",
                            onClick = { viewModel.navController.navigate(Screen.GuideAndTipsScreen.route) },
                            R.drawable.guide
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Card(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                        ) {
                            Text(text = "Reminder List")
                            LazyColumn {
                                items(filterData(reminders)) { item ->
                                    ElevatedCard(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Box {
                                            Column(
                                                modifier = Modifier
                                                    .padding(10.dp),
                                                verticalArrangement = Arrangement.spacedBy(20.dp)
                                            ) {
                                                Card {
                                                    Row(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(20.dp),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Box(
                                                            modifier = Modifier.fillMaxWidth(0.5f),
                                                            contentAlignment = Alignment.Center
                                                        ) {
                                                            Text(
                                                                text = Utils().convertTo12h(
                                                                    item.hour,
                                                                    item.minute
                                                                )[0]
                                                            )
                                                        }

                                                        Box(
                                                            modifier = Modifier.fillMaxWidth(1f),
                                                            contentAlignment = Alignment.Center
                                                        ) {
                                                            Text(
                                                                text = Utils().convertTo12h(
                                                                    item.hour,
                                                                    item.minute
                                                                )[1]
                                                            )
                                                        }
                                                    }
                                                }
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.SpaceBetween,
                                                    verticalAlignment = Alignment.Bottom
                                                ) {
                                                    TextField(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        value = item.label,
                                                        onValueChange = {},
                                                        readOnly = true,
                                                        label = { Text(text = "Label") }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeItem(
    label: String = "Patient Information",
    onClick: () -> Unit = {},
    icon: Int = R.drawable.patient_info
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(
            onClick = onClick,
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = ""
                )
            }
        }
        Text(text = label, textAlign = TextAlign.Center)
    }
}

fun filterData(reminders: MutableList<Reminder>): MutableList<Reminder> {
    // Get the current time
    val currentTime: LocalTime = LocalTime.now()

    // Extract the hour and minute
    val currentHour: Int = currentTime.hour
    val currentMinute: Int = currentTime.minute

    val filteredData = mutableListOf<Reminder>()
    reminders.map {
        if (it.hour >= currentHour && it.minute >= currentMinute) {
            filteredData.add(it)
        }
    }
    return filteredData
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(viewModel = MainViewModel())
}