package com.bmh.caretaker.screen.patient_info

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.viewmodel.MainViewModel
import java.util.Calendar

@Composable
fun PatientInformationScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
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
        var name by rememberSaveable {
            mutableStateOf("")
        }
        var age by rememberSaveable {
            mutableStateOf("")
        }
        var gender by rememberSaveable {
            mutableStateOf("Male")
        }
        var birthDate by rememberSaveable {
            mutableStateOf("")
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            WideTextField(label = "Name", value = name, onValueChange = { name = it })
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                SmallTextField(label = "Age", value = age, onValueChange = { age = it })
                SmallTextField(label = "Gender", value = gender, onValueChange = { gender = it })
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { selectDate(context) { birthDate = it }.show()  }) {
                Text(text = "Birth Date $birthDate")
            }
            WideTextField(label = "Cancer Type", value = "", onValueChange = { })
            WideTextField(label = "Cancer Stage", value = "", onValueChange = { })
        }

        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = { viewModel.navController.popBackStack() }) {
            Text(text = "Save")
        }
    }
}

fun selectDate(context: Context, birthDate: (String) -> Unit): DatePickerDialog {
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            birthDate("$mDayOfMonth/${mMonth + 1}/$mYear")
        }, mYear, mMonth, mDay
    )
    return mDatePickerDialog
}

@Composable
fun WideTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value, onValueChange = onValueChange, label = { Text(text = label) })
}

@Composable
fun ReadOnlyTextField(label: String, value: String, onClick: () -> Unit) {
    TextField(
        value = value, onValueChange = {}, label = { Text(text = label) },
        readOnly = true, modifier = Modifier.clickable {
            onClick.invoke()
        }
    )
}

@Composable
fun SmallTextField(label: String, value: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier.width(100.dp),
        value = value, onValueChange = onValueChange, label = { Text(text = label) })
}

@Preview(showBackground = true)
@Composable
fun preview() {
    PatientInformationScreen(MainViewModel())
}