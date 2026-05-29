package com.example.outdoor.feature.detailed.presentation.components

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
import com.example.outdoor.ui.theme.LightSecondary
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.LightTextSecondary

@Composable
fun SelectRoomComponent(
    priceText: String,
    nightText: String,
    onSelectRoomClick: () -> Unit,
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
                AppText.SubHeadline(text = priceText, color = LightTextPrimary)
                AppText.Body(text = nightText, color = LightTextSecondary)
            }

            Button(
                onClick = onSelectRoomClick,
                modifier = Modifier
                    .widthIn(min = 150.dp, max = 180.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = LightSecondary)
            ) {
                AppText.ButtonLabel(text = "Select Room", color = Color.White)
            }
        }
    }
}
