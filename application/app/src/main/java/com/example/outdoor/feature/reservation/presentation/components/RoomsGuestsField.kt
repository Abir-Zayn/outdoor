package com.example.outdoor.feature.reservation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.feature.reservation.presentation.GuestSelection
import com.example.outdoor.ui.theme.LightPrimary
import com.example.outdoor.ui.theme.LightSecondaryBackground
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.LightTextSecondary

@Composable
fun RoomsGuestsField(
    guests: GuestSelection,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        AppText.Label(text = "Rooms and Guests", color = LightTextSecondary)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(LightSecondaryBackground)
                .clickable { onClick() }
                .padding(horizontal = 12.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                tint = LightPrimary,
                modifier = Modifier.size(20.dp)
            )
            AppText.Body(text = guests.summary(), color = LightTextPrimary)
        }
    }
}
