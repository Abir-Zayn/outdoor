package com.example.outdoor.core.ui.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.outdoor.ui.theme.IBMPlexSerif

object AppTextStyle {
    val HeadlineLarge = TextStyle(
        fontFamily = IBMPlexSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 34.sp
    )
    val Body = TextStyle(
        fontFamily = IBMPlexSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 22.sp
    )
    val LabelNormal = TextStyle(
        fontFamily = IBMPlexSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
    val LabelSemiBold = TextStyle(
        fontFamily = IBMPlexSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
    val Badge = TextStyle(
        fontFamily = IBMPlexSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp
    )
    val ButtonLabel = TextStyle(
        fontFamily = IBMPlexSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )
}
