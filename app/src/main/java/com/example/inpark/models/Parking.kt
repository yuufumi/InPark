package com.example.inpark.models

data class Parking(
    val id: Int = 0,
    val name: String = "Leicester space",
    val description: String = "",
    val pricePerHour: Double = 100.00,
    val location: String = "39-41 Whitcomb Street",
    val isOpen: Boolean = false
)
