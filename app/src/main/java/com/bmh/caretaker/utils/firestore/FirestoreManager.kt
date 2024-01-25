package com.bmh.caretaker.utils.firestore

import android.net.Uri
import com.bmh.caretaker.model.Constant.AGE
import com.bmh.caretaker.model.Constant.BLOOD_DIA
import com.bmh.caretaker.model.Constant.BLOOD_SYS
import com.bmh.caretaker.model.Constant.CANCER_STAGE
import com.bmh.caretaker.model.Constant.CANCER_TYPE
import com.bmh.caretaker.model.Constant.CONTENT
import com.bmh.caretaker.model.Constant.DATE
import com.bmh.caretaker.model.Constant.DATE_OF_BIRTH
import com.bmh.caretaker.model.Constant.DATE_TIME
import com.bmh.caretaker.model.Constant.GENDER
import com.bmh.caretaker.model.Constant.HEART_RATE
import com.bmh.caretaker.model.Constant.IMAGE
import com.bmh.caretaker.model.Constant.LABEL
import com.bmh.caretaker.model.Constant.MONITORING
import com.bmh.caretaker.model.Constant.NAME
import com.bmh.caretaker.model.Constant.NOTES
import com.bmh.caretaker.model.Constant.OXYGEN_LEVEL
import com.bmh.caretaker.model.Constant.PATIENTS
import com.bmh.caretaker.model.Constant.REMINDER
import com.bmh.caretaker.model.Constant.TIME
import com.bmh.caretaker.model.Constant.TITLE
import com.bmh.caretaker.model.Constant.USERS
import com.bmh.caretaker.model.Notes
import com.bmh.caretaker.model.PatientInfo
import com.bmh.caretaker.model.PatientMonitor
import com.bmh.caretaker.model.Reminder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.getField
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreManager {
    val db = Firebase.firestore
    val auth = Firebase.auth

    /**
     * Upload Patient Info
     */
    fun uploadPatientInfo(
        image: Uri,
        patientInfo: PatientInfo,
        onSuccess: () -> Unit,
        onFailed: () -> Unit
    ) {
        if (auth.currentUser != null) db.collection(USERS)
            .document(auth.currentUser?.email.toString())
            .collection(PATIENTS).document("0")
            .set(patientInfo, SetOptions.merge())
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailed.invoke()
            }
    }

    /**
     * Get Patient Info
     */
    fun getPatientInfo(
        onSuccess: (PatientInfo) -> Unit,
        onFailed: () -> Unit
    ) {
        db.collection(USERS).document(auth.currentUser?.email.toString()).collection(PATIENTS)
            .document("0").get()
            .addOnSuccessListener {
                onSuccess.invoke(
                    PatientInfo(
                        name = it.getField<String>(NAME) ?: "",
                        age = it.getField<String>(AGE) ?: "",
                        gender = it.getField<String>(GENDER) ?: "",
                        dateOfBirth = it.getField<String>(DATE_OF_BIRTH) ?: "",
                        cancerType = it.getField<String>(CANCER_TYPE) ?: "",
                        cancerStage = it.getField<String>(CANCER_STAGE) ?: "",
                        image = it.getField<String>(IMAGE) ?: ""
                    )
                )
            }
            .addOnFailureListener { onFailed.invoke() }
    }

    /**
     * Upload patient monitoring data
     */
    fun uploadPatientMonitoring(
        patientMonitor: PatientMonitor,
        onSuccess: () -> Unit,
        onFailed: () -> Unit
    ) {
        db.collection(USERS).document(auth.currentUser?.email.toString()).collection(PATIENTS)
            .document("0").collection(MONITORING)
            .add(patientMonitor)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener { onFailed.invoke() }
    }

    /**
     * Get list of patient monitoring data
     */
    fun getListOfPatientMonitoring(
        onSuccess: (MutableList<PatientMonitor>) -> Unit,
        onFailed: () -> Unit
    ) {
        db.collection(USERS).document(auth.currentUser?.email.toString()).collection(PATIENTS)
            .document("0").collection(MONITORING)
            .get()
            .addOnSuccessListener { documents ->
                val list = mutableListOf<PatientMonitor>()
                documents.map {
                    list.add(
                        PatientMonitor(
                            heartRate = it.getField<String>(HEART_RATE) ?: "",
                            bloodDia = it.getField<String>(BLOOD_DIA) ?: "",
                            bloodSys = it.getField<String>(BLOOD_SYS) ?: "",
                            oxygenLevel = it.getField<String>(OXYGEN_LEVEL) ?: "",
                            dateTime = it.getField<String>(DATE_TIME) ?: ""
                        )
                    )
                }
                onSuccess.invoke(list)
            }
            .addOnFailureListener {
                onFailed.invoke()
            }
    }

    /**
     * Upload notes
     */
    fun uploadNotes(
        notes: Notes,
        onSuccess: () -> Unit,
        onFailed: () -> Unit
    ) {
        db.collection(USERS).document(auth.currentUser?.email.toString()).collection(NOTES)
            .add(notes)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailed.invoke()
            }
    }

    /**
     * Get list of notes
     */
    fun getNotes(
        onSuccess: (MutableList<Notes>) -> Unit,
        onFailed: () -> Unit
    ) {
        db.collection(USERS).document(auth.currentUser?.email.toString()).collection(NOTES)
            .get()
            .addOnSuccessListener { documents ->
                val notes = mutableListOf<Notes>()
                documents.map {
                    notes.add(
                        Notes(
                            id = it.id,
                            title = it.getField<String>(TITLE) ?: "",
                            content = it.getField<String>(CONTENT) ?: "",
                            date = it.getField<String>(DATE) ?: ""
                        )
                    )
                }
                onSuccess.invoke(notes)
            }
            .addOnFailureListener {
                onFailed.invoke()
            }
    }

    /**
     * Upload reminder
     */
    fun uploadReminder(
        reminder: Reminder,
        onSuccess: () -> Unit,
        onFailed: () -> Unit
    ) {
        db.collection(USERS).document(auth.currentUser?.email.toString()).collection(REMINDER)
            .add(reminder)
            .addOnSuccessListener {
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailed.invoke()
            }
    }

    /**
     * Get list reminder
     */
    fun getReminder(
        onSuccess: (MutableList<Reminder>) -> Unit
    ) {
        db.collection(USERS).document(auth.currentUser?.email.toString()).collection(REMINDER)
            .get()
            .addOnSuccessListener { documents ->
                val reminder = mutableListOf<Reminder>()
                documents.map {
                    reminder.add(
                        Reminder(
                            id = it.id,
                            time = it.getField<String>(TIME) ?: "",
                            label = it.getField<String>(LABEL) ?: "",
                        )
                    )
                }
            }
    }

}