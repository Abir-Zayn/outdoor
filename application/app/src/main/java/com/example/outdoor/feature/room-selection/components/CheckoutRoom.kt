package com.example.outdoor.feature.roomselection.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.ui.theme.LightPrimary
import com.example.outdoor.ui.theme.LightSecondary
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.LightTextSecondary

@Composable
fun CheckoutRoomComponent(
    selectedRoom: RoomUiModel?,
    onViewSelectedClick: () -> Unit,
    onProceedClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 20.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                if (selectedRoom != null) {
                    AppText.SubHeadline(
                        text = "BDT ${selectedRoom.pricePerNight}tk",
                        color = LightTextPrimary
                    )
                    AppText.Body(
                        text = selectedRoom.name,
                        color = LightTextSecondary
                    )
                    AppText.LabelAccent(
                        text = "View Selected",
                        modifier = Modifier.clickable { onViewSelectedClick() }
                    )
                } else {
                    AppText.SubHeadline(text = "No room selected", color = LightTextSecondary)
                    AppText.Body(text = "Pick a room to continue", color = LightTextSecondary)
                }
            }

            Button(
                onClick = onProceedClick,
                enabled = selectedRoom != null,
                modifier = Modifier
                    .widthIn(min = 140.dp, max = 170.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightPrimary,
                    disabledContainerColor = LightPrimary.copy(alpha = 0.35f)
                )
            ) {
                AppText.ButtonLabel(text = "Proceed", color = Color.White)
            }
        }
    }
}
