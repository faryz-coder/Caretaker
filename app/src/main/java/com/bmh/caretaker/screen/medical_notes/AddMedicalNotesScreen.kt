package com.bmh.caretaker.screen.medical_notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SaveAs
import androidx.compose.material.icons.rounded.Title
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.model.Notes
import com.bmh.caretaker.utils.Utils
import com.bmh.caretaker.utils.firestore.FirestoreManager
import com.bmh.caretaker.viewmodel.MainViewModel

@Composable
fun AddMedicalNotesScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val openSaveAlertDialog = remember { mutableStateOf(false) }
    var notes by remember {
        mutableStateOf(viewModel.selectedNotes?.content ?: "")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Text(text = "Medical Notes")
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f),
            value = notes, onValueChange = { notes = it }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                if (viewModel.selectedNotes != null) {
                    updateNotes(viewModel, notes,
                        onSuccess = {
                            Utils().showToast(context, "Notes updated")
                            viewModel.navController.popBackStack()
                        },
                        onFailed = {
                            Utils().showToast(context, "Failed to save notes")
                        }
                    )
                } else {
                    openSaveAlertDialog.value = true
                }
            }) {
                Text(text = "Save")
            }
        }
        var title by remember {
            mutableStateOf("")
        }
        if (openSaveAlertDialog.value) SaveAlertDialog(
            title = title,
            onConfirmation = {
                openSaveAlertDialog.value = false
                initiateSavingNotes(notes, title,
                    onSuccess = {
                        viewModel.navController.popBackStack()
                    },
                    onFailed = {
                        Utils().showToast(context, "Failed to save notes")
                        viewModel.navController.popBackStack()
                    })
            },
            onDismiss = { openSaveAlertDialog.value = false },
            onValueChange = { title = it }
        )
    }
}

fun initiateSavingNotes(notes: String, title: String, onSuccess: () -> Unit, onFailed: () -> Unit) {
    val note = Notes(
        title = title, content = notes,
        date = Utils().getCurrentDateTime()
    )
    FirestoreManager().uploadNotes(note, onSuccess = onSuccess, onFailed = onFailed)
}

fun updateNotes(viewModel: MainViewModel, note: String, onSuccess: () -> Unit, onFailed: () -> Unit) {
    val notes = viewModel.selectedNotes!!
    notes.content = note

    FirestoreManager().updateNotes(notes, onSuccess = onSuccess, onFailed = onFailed)
}

@Preview(showBackground = true)
@Composable
fun SaveAlertDialog(
    title: String = "",
    onConfirmation: () -> Unit = {},
    onDismiss: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
) {
    AlertDialog(
        icon = { Icon(Icons.Rounded.SaveAs, contentDescription = "") },
        text = {
            OutlinedTextField(
                value = title,
                onValueChange = onValueChange,
                label = { Text(text = "Title") },
                trailingIcon = { Icon(Icons.Rounded.Title, contentDescription = "") }
            )
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirmation) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Dismiss")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAddMedicalNotesScreen() {
    AddMedicalNotesScreen(viewModel = MainViewModel())
}