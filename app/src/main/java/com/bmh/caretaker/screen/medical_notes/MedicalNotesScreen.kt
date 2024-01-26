package com.bmh.caretaker.screen.medical_notes

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bmh.caretaker.model.Notes
import com.bmh.caretaker.screen.Screen
import com.bmh.caretaker.utils.Utils
import com.bmh.caretaker.utils.firestore.FirestoreManager
import com.bmh.caretaker.viewmodel.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MedicalNotesScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val notes: MutableList<Notes> = remember {
        mutableListOf()
    }
    val haptics = LocalHapticFeedback.current
    try {
        FirestoreManager().getNotes(
            onSuccess = {
                notes.clear()
                notes.addAll(it)
            },
            onFailed = {
                Utils().showToast(context, "Failed to get notes")
            }
        )
    } catch (e: Exception) {
        Log.e("MedicalNotesScreen", "Failed connecting to db: Error: $e")
    }
    var confirmationDialog by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row {
                Text(text = "History")
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    notes
                ) { note ->
                    Card(
                        modifier = Modifier
                            .combinedClickable(
                                onClick = {},
                                onLongClick = {
                                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                    confirmationDialog = true
                                }
                            )
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(text = note.title, fontSize = 15.sp)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(text = note.date, fontSize = 11.sp)
                            }
                        }
                    }
                    if (confirmationDialog) ConfirmationDialog(
                        onConfirm = {
                            try {
                                FirestoreManager().removeNotes(note)
                            } catch (e: Exception) {
                            }
                        },
                        onDismissRequest = { confirmationDialog = false }
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                viewModel.navController.navigate(Screen.AddMedicalNotesScreen.route)
            }) {
                Text(text = "Add Notes")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmationDialog(
    onConfirm: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    AlertDialog(
        icon = { Icon(Icons.Rounded.DeleteOutline, contentDescription = "") },
        title = { Text(text = "Confirm to delete?") },
        onDismissRequest = onDismissRequest, confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "Cancel")
            }
        })
}

@Preview(showBackground = true)
@Composable
fun PreviewMedicalNotesScreen() {
    MedicalNotesScreen(viewModel = MainViewModel())
}