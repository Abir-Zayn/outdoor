package com.example.outdoor.core.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.outdoor.core.ui.typography.AppTextStyle
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.LightTextSecondary
import com.example.outdoor.ui.theme.LightSecondary

object AppText {

    @Composable
    fun Headline(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = LightTextPrimary,
        textAlign: TextAlign? = null
    ) {
        Text(
            text = text,
            modifier = modifier,
            color = color,
            style = AppTextStyle.HeadlineLarge,
            textAlign = textAlign
        )
    }

    @Composable
    fun Body(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = LightTextSecondary,
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
        color: Color = LightTextPrimary
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
        color: Color = LightSecondary
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

    @Composable
    fun SubHeadline(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = LightTextPrimary
    ) {
        Text(
            text = text,
            modifier = modifier,
            color = color,
            style = AppTextStyle.SubHeadline
        )
    }

    @Composable
    fun CardTitle(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = LightTextPrimary
    ) {
        Text(
            text = text,
            modifier = modifier,
            color = color,
            style = AppTextStyle.CardTitle
        )
    }
}
