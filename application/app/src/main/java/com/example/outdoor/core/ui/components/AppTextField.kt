package com.example.outdoor.core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.typography.AppTextStyle
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.LightPrimary
import com.example.outdoor.ui.theme.LightTextSecondary
import com.example.outdoor.ui.theme.LightBackground

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    containerColor: Color = LightBackground,
    textColor: Color = LightTextPrimary,
    placeholderColor: Color = LightTextSecondary
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = if (isError) 1.dp else 0.dp,
                color = if (isError) Color.Red else Color.Transparent,
                shape = RoundedCornerShape(28.dp)
            ),
        placeholder = { Text(text = placeholder, color = placeholderColor, style = AppTextStyle.Body) },
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        shape = RoundedCornerShape(28.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            cursorColor = LightPrimary,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor
        )
    )
}
