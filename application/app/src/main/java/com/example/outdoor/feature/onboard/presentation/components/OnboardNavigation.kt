package com.example.outdoor.feature.onboard.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.LightSecondaryBackground

@Composable
fun OnboardNavigation(
    currentPage: Int,
    totalPages: Int,
    onBack: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavCircleButton(
            label = "←",
            backgroundColor = LightSecondaryBackground,
            contentColor = LightTextPrimary,
            onClick = onBack
        )

        PageDots(currentPage = currentPage, totalPages = totalPages)

        NavCircleButton(
            label = "→",
            backgroundColor = LightTextPrimary,
            contentColor = Color.White,
            onClick = onNext
        )
    }
}

@Composable
private fun NavCircleButton(
    label: String,
    backgroundColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(52.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = contentColor,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
        )
    }
}

@Composable
private fun PageDots(currentPage: Int, totalPages: Int) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        repeat(totalPages) { index ->
            val isActive = index == currentPage
            Box(
                modifier = Modifier
                    .size(if (isActive) 10.dp else 8.dp)
                    .clip(CircleShape)
                    .background(if (isActive) LightTextPrimary else LightSecondaryBackground)
            )
        }
    }
}
