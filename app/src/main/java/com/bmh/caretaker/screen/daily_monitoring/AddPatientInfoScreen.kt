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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.R
import com.bmh.caretaker.model.Daily
import com.bmh.caretaker.screen.Screen
import com.bmh.caretaker.viewmodel.MainViewModel

@Composable
fun AddPatientInfoScreen(
    viewModel: MainViewModel
) {
    var heartRate by remember { mutableStateOf("") }
    var bloodSys by remember { mutableStateOf("") }
    var bloodDia by remember { mutableStateOf("") }
    var oxygenLevel by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Starts Monitoring!")
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputCurrentStateCardOne(
                    drawable = R.drawable.heart_beat,
                    progress = heartRate,
                    onValueChange = { heartRate = it },
                    type = "BPM"
                )
                Text(text = "Heart Rate")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputCurrentStateCardTwo(
                    drawable = R.drawable.blood_pressure,
                    progress1 = bloodSys,
                    progress2 = bloodDia,
                    onValueChange1 = { bloodSys = it },
                    onValueChange2 = { bloodDia = it },
                    type1 = "BPM",
                    type2 = "BPM"
                )
                Text(text = "Blood Pressure")
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputCurrentStateCardOne(
                    drawable = R.drawable.oxygen,
                    progress = oxygenLevel,
                    onValueChange = { oxygenLevel = it },
                    type = "%"
                )
                Text(text = "Oxygen Level")
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.navController.popBackStack()
            }) {
            Text(text = "Save")
        }
    }
}

@Composable
fun InputCurrentStateCardTwo(
    drawable: Int,
    progress1: String,
    progress2: String,
    onValueChange1: (String) -> Unit,
    onValueChange2: (String) -> Unit,
    type1: String,
    type2: String
) {
    ElevatedCard(
        modifier = Modifier.height(175.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier.size(70.dp),
                painter = painterResource(drawable),
                contentDescription = "Heart Beat"
            )
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(10.dp)
                ) {
                    OutlinedTextField(
                        value = progress1, onValueChange = onValueChange1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(10.dp)
                ) {
                    OutlinedTextField(
                        value = progress2, onValueChange = onValueChange2,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth(0.4f)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceAround,
                ) {
                    Text(text = type1)
                    Text(text = type2)
                }
            }
        }
    }
}

@Composable
fun InputCurrentStateCardOne(
    drawable: Int,
    progress: String,
    onValueChange: (String) -> Unit,
    type: String
) {
    ElevatedCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier.size(70.dp),
                painter = painterResource(drawable),
                contentDescription = "Icon"
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = progress, onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(0.4f)
            ) {
                Text(text = type)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddPatientInfoScreen() {
    AddPatientInfoScreen(viewModel = MainViewModel())
}