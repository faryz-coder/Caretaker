package com.bmh.caretaker.screen.history

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.model.PatientMonitor
import com.bmh.caretaker.utils.firestore.FirestoreManager
import com.bmh.caretaker.viewmodel.MainViewModel

@Composable
fun HistoryScreen(
    viewModel: MainViewModel
) {
    val monitoringHistory: MutableList<PatientMonitor> = remember {
        mutableStateListOf()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        try {
            FirestoreManager().getListOfPatientMonitoring(
                onSuccess = {
                    monitoringHistory.clear()
                    monitoringHistory.addAll(it)
                },
                onFailed = {}
            )
        } catch (e: Exception) {
            Log.d("HistoryScreen", "Error: $e")
        }
        LazyColumn(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(monitoringHistory) { item ->
                ItemInHistory(
                    item.heartRate,
                    item.bloodSys,
                    item.bloodDia,
                    item.oxygenLevel,
                    dateTime = item.dateTime
                )
            }
        }
    }
}

@Preview
@Composable
fun ItemInHistory(
    bpm: String = "0",
    sys: String = "0",
    dia: String = "0",
    oxy: String = "0",
    dateTime: String = "26/01/2024 01:53:41"
) {
    Card {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Date: ${dateTime.split(" ")[0]}")
                    Text(text = "Time: ${dateTime.split(" ")[1]}")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ElevatedCard(
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Heart Rate", fontWeight = FontWeight.Bold)
                            Text(text = "$bpm Bpm")
                        }
                    }
                    ElevatedCard(
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Blood Pressure", fontWeight = FontWeight.Bold)
                            Text(text = "$sys Sys, $dia Dia")
                        }
                    }
                    ElevatedCard(
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Oxygen Level", fontWeight = FontWeight.Bold)
                            Text(text = "$oxy %")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistoryScreen() {
    HistoryScreen(viewModel = MainViewModel())
}