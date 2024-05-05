package com.example.intercromo.model

import java.util.Date

data class Mensaje(
    val id: String = "",
    val sender: String = "",
    val message: String = "",
    val timestamp: Date = Date()
)