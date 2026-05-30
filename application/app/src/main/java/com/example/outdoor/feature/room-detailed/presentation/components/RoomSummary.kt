package com.example.outdoor.feature.roomdetailed.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.AspectRatio
import androidx.compose.material.icons.filled.SmokeFree
import androidx.compose.material.icons.outlined.Window
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.ui.theme.LightPrimary
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.LightTextSecondary

@Composable
fun RoomSummary(
    title: String,
    bedSize: String,
    guestCount: Int,
    roomType: String,
    characteristics: String,
    smokingPolicy: String,
    roomSizeSqft: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        AppText.Headline(text = title, color = LightTextPrimary)

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoomSummaryChip(
                icon = { Icon(Icons.Filled.Bed, contentDescription = null, tint = LightPrimary, modifier = Modifier.size(18.dp)) },
                text = bedSize
            )
            RoomSummaryChip(
                icon = { Icon(Icons.Filled.Group, contentDescription = null, tint = LightPrimary, modifier = Modifier.size(18.dp)) },
                text = "$guestCount Guests"
            )
        }

        Divider(color = LightTextSecondary.copy(alpha = 0.15f), thickness = 1.dp)

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            RoomInfoRow(
                icon = { Icon(Icons.Filled.Hotel, contentDescription = null, tint = LightPrimary, modifier = Modifier.size(18.dp)) },
                label = "Room Type",
                value = roomType
            )
            RoomInfoRow(
                icon = { Icon(Icons.Outlined.Window, contentDescription = null, tint = LightPrimary, modifier = Modifier.size(18.dp)) },
                label = "Room Characteristics",
                value = characteristics
            )
            RoomInfoRow(
                icon = { Icon(Icons.Filled.SmokeFree, contentDescription = null, tint = LightPrimary, modifier = Modifier.size(18.dp)) },
                label = "Smoking Policy",
                value = smokingPolicy
            )
            RoomInfoRow(
                icon = { Icon(Icons.Filled.AspectRatio, contentDescription = null, tint = LightPrimary, modifier = Modifier.size(18.dp)) },
                label = "Room Size",
                value = "$roomSizeSqft sqft"
            )
        }
    }
}

@Composable
private fun RoomSummaryChip(
    icon: @Composable () -> Unit,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        icon()
        AppText.Label(text = text, color = LightTextSecondary)
    }
}

@Composable
private fun RoomInfoRow(
    icon: @Composable () -> Unit,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        icon()
        AppText.Label(text = label, color = LightTextSecondary, modifier = Modifier.weight(1f))
        AppText.LabelAccent(text = value, color = LightTextPrimary)
    }
}
