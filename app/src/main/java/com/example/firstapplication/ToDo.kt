package com.example.firstapplication

data class ToDo(
    val id: String,
    val userId: String,
    val title: String?,
    var completed: Boolean
)