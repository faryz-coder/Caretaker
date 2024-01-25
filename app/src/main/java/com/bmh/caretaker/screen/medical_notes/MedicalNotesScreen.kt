package com.bmh.caretaker.screen.medical_notes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bmh.caretaker.model.Notes
import com.bmh.caretaker.screen.Screen
import com.bmh.caretaker.utils.Utils
import com.bmh.caretaker.utils.firestore.FirestoreManager
import com.bmh.caretaker.viewmodel.MainViewModel

@Composable
fun MedicalNotesScreen(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val notes: MutableList<Notes> = remember {
        mutableListOf()
    }
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
        Utils().showToast(context, "Failed connecting to db")
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
                    Card {
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
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Delete")
            }
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
fun PreviewMedicalNotesScreen() {
    MedicalNotesScreen(viewModel = MainViewModel())
}