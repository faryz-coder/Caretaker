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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.R
import com.bmh.caretaker.model.Reminder
import com.bmh.caretaker.screen.Screen
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
                        onClick = {
                            viewModel.navController.navigate(Screen.PatientInformationScreen.route)
                        },
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.patient_info),
                                contentDescription = ""
                            )
//                            Text(text = "Patient Information", textAlign = TextAlign.Center)
                        }
                    }
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        onClick = { viewModel.navController.navigate(Screen.DailyMonitoring.route) },
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.daily_monitoring),
                                contentDescription = ""
                            )
//                            Text(text = "Daily Monitoring")
                        }
                    }
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        onClick = {
                            viewModel.navController.navigate(Screen.ReminderScreen.route)
                        },
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.reminder),
                                contentDescription = ""
                            )
//                            Text(text = "Reminder", textAlign = TextAlign.Center)
                        }
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
                        onClick = {
                            viewModel.navController.navigate(Screen.MedicalNotesScreen.route)
                        },
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.medical_notes),
                                contentDescription = ""
                            )
//                            Text(text = "Medical Notes", textAlign = TextAlign.Center)
                        }
                    }
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        onClick = {
                            viewModel.navController.navigate(Screen.DietGuideScreen.route)
                        },
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.diet),
                                contentDescription = ""
                            )
//                            Text(text = "Diet Guides", textAlign = TextAlign.Center)
                        }
                    }
                    ElevatedCard(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        onClick = {
                            viewModel.navController.navigate(Screen.GuideAndTipsScreen.route)
                        },
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.guide),
                                contentDescription = ""
                            )
//                            Text(text = "Guide & Tips", textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(top = 20.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .padding(20.dp),
                ) {
                    Text(text = "Reminder List")
                    LazyColumn {
                        items(filterData(reminders)) {item ->
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
                                                        text = "${item.hour}:${if (item.minute < 10) 0 else ""}${item.minute}"
                                                    )
                                                }

                                                Box(
                                                    modifier = Modifier.fillMaxWidth(1f),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Text(text = "AM/PM")
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
                                                value = item.label, onValueChange = {}, readOnly = true,
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

fun filterData(reminders: MutableList<Reminder>): MutableList<Reminder> {
    // Get the current time
    val currentTime: LocalTime = LocalTime.now()

    // Extract the hour and minute
    val currentHour: Int = currentTime.hour
    val currentMinute: Int = currentTime.minute

    val filteredData = mutableListOf<Reminder>()
    reminders.map {
        if (it.hour < currentHour && it.minute < currentMinute) {
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