package com.example.outdoor.feature.roomselection.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.EventSeat
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.KingBed
import androidx.compose.material.icons.filled.SmokeFree
import androidx.compose.material.icons.filled.SmokingRooms
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.ui.theme.LightPrimary
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.LightTextSecondary
import com.example.outdoor.ui.theme.OutdoorGold

data class RoomUiModel(
    val id: Int,
    val name: String,
    val bedSize: String,
    val guestCount: Int,
    val accentColor: Color = Color(0xFF5C8FB8),
    val pricePerNight: Int = 0,
    val warning: String? = null,
    val smokingAllowed: Boolean = false,
    val seatsLeft: Int = 0
)

@Composable
fun HotelRoomCard(
    room: RoomUiModel,
    modifier: Modifier = Modifier,
    onClick: (RoomUiModel) -> Unit = {},
    onViewDetails: (RoomUiModel) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clickable { onClick(room) },
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(room.accentColor, room.accentColor.copy(alpha = 0.7f))
                    )
                )
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AppText.CardTitle(text = room.name, color = LightTextPrimary)

            RoomSpec(icon = Icons.Filled.KingBed, label = room.bedSize)
            RoomSpec(icon = Icons.Filled.Group, label = "${room.guestCount} Guests")

            room.warning?.let {
                RoomSpec(icon = Icons.Filled.Warning, label = it, tint = OutdoorGold)
            }

            RoomSpec(
                icon = if (room.smokingAllowed) Icons.Filled.SmokingRooms else Icons.Filled.SmokeFree,
                label = if (room.smokingAllowed) "Smoking" else "Non-Smoking"
            )

            RoomSpec(icon = Icons.Filled.EventSeat, label = "${room.seatsLeft} Seats Left")

            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable { onViewDetails(room) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                AppText.Label(text = "View Details", color = LightPrimary)
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = LightPrimary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
private fun RoomSpec(
    icon: ImageVector,
    label: String,
    tint: Color = LightPrimary,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(18.dp)
        )
        AppText.Body(text = label, color = LightTextSecondary)
    }
}
