package com.example.outdoor.feature.reservation.presentation

import androidx.compose.ui.graphics.Color

data class ReservationHotelUiModel(
    val name: String,
    val location: String,
    val rating: Double,
    val accentColor: Color = Color(0xFF5C8FB8)
)

data class GuestSelection(
    val rooms: Int = 3,
    val adults: Int = 5,
    val children: Int = 1,
    val infants: Int = 0
) {
    fun summary(): String = "$rooms Rooms | $adults Adults • $children Children"
}
