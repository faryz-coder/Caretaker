package com.bmh.caretaker.model

data class Notes(
    val id: String = "",
    val title: String,
    var content: String,
    val date: String
)
