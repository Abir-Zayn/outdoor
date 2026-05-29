package com.example.outdoor.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val OutdoorColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = LightSecondary,
    background = LightBackground,
    surface = LightBackground,
    onPrimary = Color.White,
    onSecondary = LightTextPrimary,
    onBackground = LightTextPrimary,
    onSurface = LightTextPrimary,
)

@Composable
fun OutdoorTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = OutdoorColorScheme,
        typography = OutdoorTypography,
        content = content
    )
}
