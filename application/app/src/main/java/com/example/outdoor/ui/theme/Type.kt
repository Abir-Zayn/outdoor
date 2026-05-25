package com.example.outdoor.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.outdoor.R

val IBMPlexSerif = FontFamily(
    Font(R.font.ibm_plex_serif_regular, FontWeight.Normal),
    Font(R.font.ibm_plex_serif_medium, FontWeight.Medium),
    Font(R.font.ibm_plex_serif_semibold, FontWeight.SemiBold),
    Font(R.font.ibm_plex_serif_light, FontWeight.Light),
    Font(R.font.ibm_plex_serif_italic, FontWeight.Normal, FontStyle.Italic),
)

val OutdoorTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = IBMPlexSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp,
        lineHeight = 42.sp,
        letterSpacing = 0.02.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = IBMPlexSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = IBMPlexSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    )
)
