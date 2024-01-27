package com.bmh.caretaker.screen.daily_monitoring

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.History
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.R
import com.bmh.caretaker.screen.Screen
import com.bmh.caretaker.utils.Utils
import com.bmh.caretaker.utils.firestore.FirestoreManager
import com.bmh.caretaker.viewmodel.MainViewModel

@Composable
fun DailyMonitoringScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    var heartBeatProgress by remember {
        mutableStateOf("90")
    }
    var sysProgress by remember {
        mutableStateOf("90")
    }
    var diaProgress by remember {
        mutableStateOf("90")
    }
    var oxygenLevelProgress by remember {
        mutableStateOf("96")
    }
    var patientName by remember {
        mutableStateOf("Patients Name")
    }
    var base64Image: ByteArray? = null
    var bitmap: Bitmap? by rememberSaveable {
        mutableStateOf(null)
    }

    try {
        FirestoreManager().getPatientInfo(
            onSuccess = {
                patientName = it.name
                base64Image = Utils().imageFromBase64(it.image)
                bitmap = base64Image?.let { byteArray ->
                    BitmapFactory.decodeByteArray(
                        base64Image,
                        0,
                        byteArray.size
                    )
                }
            },
            onFailed = {
                Utils().showToast(context, "Failed retrieving patient info")
            }
        )

        FirestoreManager().getListOfPatientMonitoring(
            onSuccess = {
                if (it.size > 0) {
                    val info = it.first()
                    heartBeatProgress = info.heartRate
                    sysProgress = info.bloodSys
                    diaProgress = info.bloodDia
                    oxygenLevelProgress = info.oxygenLevel
                }
            },
            onFailed = {
                Utils().showToast(context, "Failed retrieving data")
            }
        )
    } catch (e: Exception) { }

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
                    if (bitmap == null) Icon(
                        Icons.Filled.PersonOutline,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                    ) else Image(
                        bitmap = bitmap!!.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(onClick = {
                    viewModel.navController.navigate(Screen.HistoryScreen.route)
                }) {
                    Icon(imageVector = Icons.Rounded.History, contentDescription = "History Button")
                }
            }
        }

        // Patient name
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = patientName)
        }

        // Add New
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                viewModel.navController.navigate(Screen.AddPatientInfoScreen.route)
            }) {
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

        CurrentStateCardOne(
            label = "Heart Rate",
            drawable = R.drawable.heart_beat,
            progress = heartBeatProgress,
            type = "BPM"
        )

        CurrentStateCardTwo(
            label = "Blood Pressure",
            drawable = R.drawable.blood_pressure,
            progress1 = sysProgress,
            progress2 = diaProgress,
            type1 = "SYS",
            type2 = "DIA"
        )


        // Oxygen Level
        CurrentStateCardOne(
            label = "Oxygen Level",
            drawable = R.drawable.oxygen,
            progress = oxygenLevelProgress,
            type = "%"
        )
    }
}

@Composable
fun CurrentStateCardTwo(
    label: String,
    drawable: Int,
    progress1: String,
    progress2: String,
    type1: String,
    type2: String
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
                contentDescription = "Heart Beat"
            )
            Text(
                text = label,
                modifier = Modifier.width(80.dp),
                textAlign = TextAlign.Center
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Card {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = progress1)
                    }
                }
                Card {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = progress2)
                    }
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth(0.4f)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    Text(text = type1)
                    Text(text = type2)
                }
            }
        }
    }
}

@Composable
fun CurrentStateCardOne(
    label: String,
    drawable: Int,
    progress: String,
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
                contentDescription = "Heart Beat"
            )
            Text(
                text = label,
                modifier = Modifier.width(80.dp),
                textAlign = TextAlign.Center
            )
            Card {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = progress)
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth(0.4f)
            ) {
                Text(text = type)
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