package com.example.outdoor.feature.reservation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.ui.theme.LightPrimary
import com.example.outdoor.ui.theme.LightSecondaryBackground
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.LightTextSecondary
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private val weekdays = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")
private val rangeHighlight = Color(0xFFE3ECFF)

@Composable
fun ReservationDateComponent(
    displayedMonth: Calendar,
    checkIn: Int?,
    checkOut: Int?,
    onPrevMonth: () -> Unit,
    onNextMonth: () -> Unit,
    onDayClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val monthFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    val daysInMonth = displayedMonth.getActualMaximum(Calendar.DAY_OF_MONTH)

    // First weekday offset (Calendar.SUNDAY == 1 → index 0)
    val firstOfMonth = (displayedMonth.clone() as Calendar).apply {
        set(Calendar.DAY_OF_MONTH, 1)
    }
    val firstWeekdayOffset = firstOfMonth.get(Calendar.DAY_OF_WEEK) - 1

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Month header with prev/next
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onPrevMonth, modifier = Modifier.size(32.dp)) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = "Previous month",
                    tint = LightTextPrimary
                )
            }
            AppText.CardTitle(
                text = monthFormat.format(displayedMonth.time),
                color = LightTextPrimary
            )
            IconButton(onClick = onNextMonth, modifier = Modifier.size(32.dp)) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = "Next month",
                    tint = LightTextPrimary
                )
            }
        }

        // Weekday header
        Row(modifier = Modifier.fillMaxWidth()) {
            weekdays.forEach { day ->
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    AppText.Label(text = day, color = LightTextSecondary)
                }
            }
        }

        // Day grid — 6 rows of 7 cells
        val totalCells = firstWeekdayOffset + daysInMonth
        val rows = (totalCells + 6) / 7
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            for (row in 0 until rows) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (col in 0 until 7) {
                        val cellIndex = row * 7 + col
                        val day = cellIndex - firstWeekdayOffset + 1
                        Box(modifier = Modifier.weight(1f)) {
                            if (day in 1..daysInMonth) {
                                DayCell(
                                    day = day,
                                    checkIn = checkIn,
                                    checkOut = checkOut,
                                    onClick = { onDayClick(day) }
                                )
                            }
                        }
                    }
                }
            }
        }

        // Check-In / Check-Out fields
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DateField(
                label = "Check-In",
                value = formatDay(displayedMonth, checkIn),
                modifier = Modifier.weight(1f)
            )
            DateField(
                label = "Check-Out",
                value = formatDay(displayedMonth, checkOut),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun DayCell(
    day: Int,
    checkIn: Int?,
    checkOut: Int?,
    onClick: () -> Unit
) {
    val isEndpoint = day == checkIn || day == checkOut
    val inRange = checkIn != null && checkOut != null && day > checkIn && day < checkOut

    val background = when {
        isEndpoint -> LightPrimary
        inRange -> rangeHighlight
        else -> Color.Transparent
    }
    val textColor = if (isEndpoint) Color.White else LightTextPrimary

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(if (isEndpoint) CircleShape else RoundedCornerShape(8.dp))
            .background(background)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        AppText.Body(
            text = day.toString(),
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun DateField(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        AppText.Label(text = label, color = LightTextSecondary)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(LightSecondaryBackground)
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = null,
                tint = LightTextSecondary,
                modifier = Modifier.size(18.dp)
            )
            AppText.Body(text = value, color = LightTextPrimary)
        }
    }
}

private fun formatDay(month: Calendar, day: Int?): String {
    if (day == null) return "Select date"
    val cal = (month.clone() as Calendar).apply { set(Calendar.DAY_OF_MONTH, day) }
    return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(cal.time)
}
