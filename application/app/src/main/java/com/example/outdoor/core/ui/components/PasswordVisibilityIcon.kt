package com.example.outdoor.core.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.example.outdoor.ui.theme.LightTextSecondary

@Composable
fun PasswordVisibilityIcon(isVisible: Boolean, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(24.dp)) {
        val strokeWidth = 2.dp.toPx()
        val color = LightTextSecondary

        if (isVisible) {
            // Draw open eye arc
            drawArc(
                color = color,
                startAngle = 10f,
                sweepAngle = 160f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
            )
            drawArc(
                color = color,
                startAngle = 190f,
                sweepAngle = 160f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
            )
            drawCircle(
                color = color,
                radius = 4.dp.toPx()
            )
        } else {
            // Draw closed/slash eye arc
            drawArc(
                color = color,
                startAngle = 10f,
                sweepAngle = 160f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
            )
            drawArc(
                color = color,
                startAngle = 190f,
                sweepAngle = 160f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
            )
            drawCircle(
                color = color,
                radius = 4.dp.toPx()
            )
            // Slash line
            drawLine(
                color = color,
                start = Offset(4.dp.toPx(), 4.dp.toPx()),
                end = Offset(20.dp.toPx(), 20.dp.toPx()),
                strokeWidth = strokeWidth
            )
        }
    }
}
