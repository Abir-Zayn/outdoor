package com.example.outdoor.feature.reservation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppButton
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.feature.reservation.presentation.GuestSelection
import com.example.outdoor.ui.theme.LightPrimary
import com.example.outdoor.ui.theme.LightSecondaryBackground
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.LightTextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomsGuestsBottomSheet(
    initial: GuestSelection,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onConfirm: (GuestSelection) -> Unit
) {
    var rooms by remember { mutableIntStateOf(initial.rooms) }
    var adults by remember { mutableIntStateOf(initial.adults) }
    var children by remember { mutableIntStateOf(initial.children) }
    var infants by remember { mutableIntStateOf(initial.infants) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AppText.SubHeadline(
                text = "Select Rooms and Guests",
                color = LightTextPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )

            HorizontalDivider(color = LightSecondaryBackground)

            GuestCounterRow(
                title = "Rooms",
                subtitle = "How many rooms?",
                count = rooms,
                minValue = 1,
                onMinus = { rooms-- },
                onPlus = { rooms++ }
            )
            GuestCounterRow(
                title = "Adults",
                subtitle = "Ages 18 or Above",
                count = adults,
                minValue = 1,
                onMinus = { adults-- },
                onPlus = { adults++ }
            )
            GuestCounterRow(
                title = "Children",
                subtitle = "Ages 2-17",
                count = children,
                minValue = 0,
                onMinus = { children-- },
                onPlus = { children++ }
            )
            GuestCounterRow(
                title = "Infants",
                subtitle = "Under Ages 2",
                count = infants,
                minValue = 0,
                onMinus = { infants-- },
                onPlus = { infants++ }
            )

            AppButton(
                text = "Confirm",
                onClick = {
                    onConfirm(GuestSelection(rooms, adults, children, infants))
                },
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

@Composable
private fun GuestCounterRow(
    title: String,
    subtitle: String,
    count: Int,
    minValue: Int,
    onMinus: () -> Unit,
    onPlus: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            AppText.CardTitle(text = title, color = LightTextPrimary)
            AppText.Body(text = subtitle, color = LightTextSecondary)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            StepperButton(
                icon = { Icon(Icons.Filled.Remove, contentDescription = "Decrease", tint = LightTextPrimary) },
                background = LightSecondaryBackground,
                enabled = count > minValue,
                onClick = onMinus
            )
            AppText.CardTitle(text = count.toString(), color = LightTextPrimary)
            StepperButton(
                icon = { Icon(Icons.Filled.Add, contentDescription = "Increase", tint = Color.White) },
                background = LightPrimary,
                enabled = true,
                onClick = onPlus
            )
        }
    }
}

@Composable
private fun StepperButton(
    icon: @Composable () -> Unit,
    background: Color,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val bg = if (enabled) background else background.copy(alpha = 0.4f)
    Row(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(bg)
            .clickable(enabled = enabled, onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        icon()
    }
}
