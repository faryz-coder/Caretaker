package com.bmh.caretaker.model

data class Reminder (
    val id: String = "",
    val time: String = "",
    val label: String = "",
    val checked: Boolean = false
)