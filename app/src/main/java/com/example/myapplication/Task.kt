package com.example.myapplication

data class Task(
    val id: Long = System.currentTimeMillis(), // Unique ID for stable list operations
    val text: String,
    var isFavorite: Boolean = false
)