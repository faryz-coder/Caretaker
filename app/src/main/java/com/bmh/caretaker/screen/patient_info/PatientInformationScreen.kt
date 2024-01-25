package com.bmh.caretaker.screen.patient_info

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bmh.caretaker.model.PatientInfo
import com.bmh.caretaker.utils.Utils
import com.bmh.caretaker.utils.firestore.FirestoreManager
import com.bmh.caretaker.viewmodel.MainViewModel
import java.util.Calendar

@Composable
fun PatientInformationScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    var updateImg: Uri? by remember {
        mutableStateOf(null)
    }

    val painter = rememberAsyncImagePainter(model = updateImg)

    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            updateImg = it
        }
    )

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
    var cancerType by rememberSaveable {
        mutableStateOf("")
    }
    var cancerStage by rememberSaveable {
        mutableStateOf("")
    }
    var base64Image: ByteArray? = null
    var bitmap: Bitmap? by rememberSaveable {
        mutableStateOf(null)
    }

    try {
        FirestoreManager().getPatientInfo(
            onSuccess = {
                name = it.name
                age = it.age
                gender = it.gender
                birthDate = it.dateOfBirth
                cancerType = it.cancerType
                cancerStage = it.cancerStage
                base64Image = Utils().imageFromBase64(it.image)

                bitmap = base64Image?.let { byteArray ->
                    BitmapFactory.decodeByteArray(
                        base64Image,
                        0,
                        byteArray.size
                    )
                }
            },
            onFailed = {}
        )
    } catch (_: Exception) {
    }


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
            ElevatedCard(
                onClick = {
                    pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            ) {
                Box(
                    modifier = Modifier.size(150.dp)
                ) {

                    if (updateImg != null) Image(
                        painter = painter,
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxSize(),
                    ) else if (bitmap != null) Image(
                        bitmap = bitmap!!.asImageBitmap(),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxSize(),
                    )
                    else Icon(
                        Icons.Filled.AddAPhoto,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                    )
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            WideTextField(label = "Name", value = name, onValueChange = { name = it })
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                SmallTextField(
                    label = "Age",
                    value = age,
                    isNumber = true,
                    onValueChange = { age = it })
                GenderDropdown { gender = it }
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { selectDate(context) { birthDate = it }.show() }) {
                Text(text = "Birth Date $birthDate")
            }
            WideTextField(
                label = "Cancer Type",
                value = cancerType,
                onValueChange = { cancerType = it })
            WideTextField(
                label = "Cancer Stage",
                value = cancerStage,
                onValueChange = { cancerStage = it })
        }

        Button(
            modifier = Modifier.fillMaxWidth(0.5f),
            onClick = {
//                viewModel.navController.popBackStack()
                Log.d(
                    "PatientInfo",
                    "name: $name, age: $age, gender: $gender, dob: $birthDate, ctype: $cancerType, cStage: $cancerStage"
                )
                initiatePatientInfoUpdate(
                    context, name, age, gender, birthDate, cancerType, cancerStage, updateImg
                )

            }) {
            Text(text = "Save")
        }
    }
}

fun initiatePatientInfoUpdate(
    context: Context,
    name: String,
    age: String,
    gender: String,
    birthDate: String,
    cancerType: String,
    cancerStage: String,
    updateImg: Uri?
) {
    if (name.isNotEmpty() && age.isNotEmpty() && gender.isNotEmpty() && birthDate.isNotEmpty() && cancerType.isNotEmpty()
        && cancerStage.isNotEmpty() && updateImg != null
    ) {
        val stringBase64 = Utils().convertImageToBase64(context, updateImg) ?: ""
        PatientInfo(
            name, age, gender, birthDate, cancerType, cancerStage, stringBase64
        ).let {
            try {
                FirestoreManager().uploadPatientInfo(
                    patientInfo = it,
                    onSuccess = { Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show() },
                    onFailed = {}
                )
            } catch (e: Exception) {
                Log.e("PatientInfo", "initiatePatientInfoUpdate:Error:$e")
            }
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
fun SmallTextField(
    label: String,
    value: String,
    isNumber: Boolean = false,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier.width(100.dp),
        value = value, onValueChange = onValueChange, label = { Text(text = label) },
        keyboardOptions = if (isNumber) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions(
            keyboardType = KeyboardType.Text
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderDropdown(onSelect: (String) -> Unit) {
    val options = listOf("Male", "Female")
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text("Gender") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(text = selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        onSelect.invoke(selectionOption)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {
    PatientInformationScreen(MainViewModel())
}