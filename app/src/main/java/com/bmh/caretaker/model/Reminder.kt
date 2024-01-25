package com.bmh.caretaker.model

data class Reminder (
    var id: String = "",
    val hour: Int = 0,
    val minute: Int = 0,
    val label: String = "",
    var checked: Boolean = false
)