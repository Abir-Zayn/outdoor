package com.example.outdoor.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val OutdoorColorScheme = lightColorScheme(
    primary = OutdoorBlue,
    secondary = OutdoorGold,
    background = OutdoorNearWhite,
    surface = OutdoorNearWhite,
    onPrimary = Color.White,
    onSecondary = OutdoorBlack,
    onBackground = OutdoorBlack,
    onSurface = OutdoorBlack,
)

@Composable
fun OutdoorTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = OutdoorColorScheme,
        typography = OutdoorTypography,
        content = content
    )
}
