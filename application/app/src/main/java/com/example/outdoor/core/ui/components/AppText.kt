package com.example.outdoor.core.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.outdoor.core.ui.typography.AppTextStyle
import com.example.outdoor.ui.theme.OutdoorBlack
import com.example.outdoor.ui.theme.OutdoorGray
import com.example.outdoor.ui.theme.OutdoorOrange

object AppText {

    @Composable
    fun Body(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = OutdoorGray,
        textAlign: TextAlign? = null
    ) {
        Text(
            text = text,
            modifier = modifier,
            color = color,
            style = AppTextStyle.Body,
            textAlign = textAlign
        )
    }

    @Composable
    fun Label(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = OutdoorBlack
    ) {
        Text(
            text = text,
            modifier = modifier,
            color = color,
            style = AppTextStyle.LabelNormal
        )
    }

    @Composable
    fun LabelAccent(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = OutdoorOrange
    ) {
        Text(
            text = text,
            modifier = modifier,
            color = color,
            style = AppTextStyle.LabelSemiBold
        )
    }

    @Composable
    fun Badge(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.White
    ) {
        Text(
            text = text,
            modifier = modifier,
            color = color,
            style = AppTextStyle.Badge
        )
    }

    @Composable
    fun ButtonLabel(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.White
    ) {
        Text(
            text = text,
            modifier = modifier,
            color = color,
            style = AppTextStyle.ButtonLabel
        )
    }
}
