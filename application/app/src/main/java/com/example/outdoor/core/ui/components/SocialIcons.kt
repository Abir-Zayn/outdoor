package com.example.outdoor.core.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.outdoor.ui.theme.OutdoorLightGray

@Composable
fun SocialIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun FacebookIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(44.dp)
            .background(Color(0xFF1877F2), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "f",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier.padding(bottom = 2.dp)
        )
    }
}

@Composable
fun GoogleIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(44.dp)
            .background(Color.White, shape = CircleShape)
            .border(1.dp, OutdoorLightGray, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(20.dp)) {
            val strokeWidth = 3.dp.toPx()

            // Draw a beautiful circular Google 'G' shape using canvas arcs
            drawArc(
                color = Color(0xFFEA4335), // Red
                startAngle = 180f,
                sweepAngle = 90f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
            )
            drawArc(
                color = Color(0xFFFBBC05), // Yellow
                startAngle = 90f,
                sweepAngle = 90f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
            )
            drawArc(
                color = Color(0xFF34A853), // Green
                startAngle = 0f,
                sweepAngle = 90f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
            )
            drawArc(
                color = Color(0xFF4285F4), // Blue
                startAngle = 270f,
                sweepAngle = 90f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
            )
        }
    }
}

@Composable
fun AppleIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(44.dp)
            .background(Color.Black, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(20.dp)) {
            // Draw a simplified silhouette vector of an Apple
            val path = Path().apply {
                moveTo(size.width * 0.5f, size.height * 0.9f)
                cubicTo(
                    size.width * 0.3f, size.height * 0.95f,
                    size.width * 0.1f, size.height * 0.7f,
                    size.width * 0.1f, size.height * 0.4f
                )
                cubicTo(
                    size.width * 0.1f, size.height * 0.15f,
                    size.width * 0.35f, size.height * 0.15f,
                    size.width * 0.5f, size.height * 0.25f
                )
                cubicTo(
                    size.width * 0.65f, size.height * 0.15f,
                    size.width * 0.9f, size.height * 0.15f,
                    size.width * 0.9f, size.height * 0.4f
                )
                cubicTo(
                    size.width * 0.9f, size.height * 0.7f,
                    size.width * 0.7f, size.height * 0.95f,
                    size.width * 0.5f, size.height * 0.9f
                )
                close()
            }
            drawPath(path = path, color = Color.White)

            // Draw leaf of apple
            val leafPath = Path().apply {
                moveTo(size.width * 0.5f, size.height * 0.2f)
                cubicTo(
                    size.width * 0.55f, 0f,
                    size.width * 0.7f, 0f,
                    size.width * 0.75f, size.height * 0.05f
                )
                cubicTo(
                    size.width * 0.7f, size.height * 0.15f,
                    size.width * 0.55f, size.height * 0.2f,
                    size.width * 0.5f, size.height * 0.2f
                )
                close()
            }
            drawPath(path = leafPath, color = Color.White)
        }
    }
}
