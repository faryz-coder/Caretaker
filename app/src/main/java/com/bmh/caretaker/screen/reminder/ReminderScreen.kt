package com.bmh.caretaker.screen.reminder

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditNotifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bmh.caretaker.broadcast.NotificationManager
import com.bmh.caretaker.model.Reminder
import com.bmh.caretaker.screen.medical_notes.ConfirmationDialog
import com.bmh.caretaker.utils.Utils
import com.bmh.caretaker.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun ReminderScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val addAlertDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        val reminders: MutableList<Reminder> = remember { mutableStateListOf() }

        try {
            viewModel.sharedPreferenceManager.getListReminder().let {
                reminders.clear()
                reminders.addAll(it)
            }

        } catch (e: Exception) {
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(
                reminders
            ) { item ->
                ReminderBox(viewModel, item,
                    onChange = {
                        viewModel.sharedPreferenceManager.updateReminderStatus(item, it)
                        reminders.map { itm ->
                            if (itm == item) {
                                itm.checked = it
                            }
                        }
                        if (it) {
                            NotificationManager().createNotification(
                                context = context,
                                requestCode = reminders.indexOf(item),
                                label = item.label,
                                hour = item.hour,
                                minute = item.minute
                            )
                        }
                    },
                    reminders.indexOf(item),
                    onDeletion = { reminders.remove(item) }
                )
            }
        }
        ElevatedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                addAlertDialog.value = true
            }) {
            Text(text = "Add Reminder")
        }
        if (addAlertDialog.value) {
            DialogAddReminder(
                viewModel,
                onDismissRequest = {
                    addAlertDialog.value = false
                },

                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DialogAddReminder(
    viewModel: MainViewModel = MainViewModel(),
    onDismissRequest: () -> Unit = {},
    onConfirmation: (String, String) -> Unit = { _, _ -> },
) {
    val context = LocalContext.current
    val state = rememberTimePickerState()
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
    val snackScope = rememberCoroutineScope()
    val snackState = remember { SnackbarHostState() }
    var label by remember { mutableStateOf("") }

    AlertDialog(
        icon = { Icon(Icons.Rounded.EditNotifications, contentDescription = "Reminder") },
        title = { Text(text = "Create Reminder") },
        text = {
            Column {
                TimePicker(state = state)
                OutlinedTextField(
                    label = { Text(text = "Reminder Label") },
                    value = label, onValueChange = { label = it })
            }
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = {
                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, state.hour)
                cal.set(Calendar.MINUTE, state.minute)
                cal.isLenient = false
                Log.d("ReminderScreen", "$cal")
                if (label.isNotEmpty()) {
                    viewModel.sharedPreferenceManager.addReminderInto(
                        Reminder(
                            hour = state.hour,
                            minute = state.minute,
                            label = label
                        )
                    )
                    onDismissRequest.invoke()
                } else {
                    Utils().showToast(context = context, "Missing Label")
                }
            }) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "Dismiss")
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun ReminderBox(
    viewModel: MainViewModel = MainViewModel(),
    reminder: Reminder = Reminder(),
    onChange: (Boolean) -> Unit = {},
    index: Int = 0,
    onDeletion: () -> Unit = {}
) {
    val context = LocalContext.current
    var currentState by remember {
        mutableStateOf(reminder.checked)
    }
    var confirmationDialog by remember {
        mutableStateOf(false)
    }
    if (confirmationDialog) {
        ConfirmationDialog(
            onConfirm = {
                viewModel.sharedPreferenceManager.removeItem(reminder.id)
                if (reminder.checked) {
                    /* Remove notification if it currently enabled */
                    NotificationManager().removeNotification(context, index)
                }
                confirmationDialog = false
                onDeletion.invoke()
            },
            onDismissRequest = { confirmationDialog = false }
        )
    }
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .combinedClickable(
                    onClick = {},
                    onLongClick = {
                        confirmationDialog = true
                    }
                )
        ) {
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
                                text = Utils().convertTo12h(reminder.hour, reminder.minute)[0]
                            )
                        }

                        Box(
                            modifier = Modifier.fillMaxWidth(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = Utils().convertTo12h(reminder.hour, reminder.minute)[1])
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        value = reminder.label, onValueChange = {}, readOnly = true,
                        label = { Text(text = "Label") }
                    )
                    Switch(checked = currentState, onCheckedChange = {
                        currentState = it
                        onChange.invoke(it)
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReminderScreen() {
    ReminderScreen(viewModel = MainViewModel())
}