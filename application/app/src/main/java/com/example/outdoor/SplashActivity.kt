package com.example.outdoor

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.caverock.androidsvg.SVGImageView
import com.example.outdoor.ui.theme.IBMPlexSerif
import com.example.outdoor.ui.theme.OutdoorBlue
import com.example.outdoor.ui.theme.OutdoorTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OutdoorTheme {
                SplashScreen(
                    onTimeout = {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
        delay(2500)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OutdoorBlue.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 700, delayMillis = 200))
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Outdoor.",
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = IBMPlexSerif,
                    letterSpacing = 0.02.sp
                )
                Spacer(modifier = Modifier.width(10.dp))
                // SVG logo loaded from assets/app_logo.svg via androidsvg wrapped in AndroidView
                AndroidView(
                    factory = { context ->
                        SVGImageView(context).apply {
                            setImageAsset("app_logo.svg")
                        }
                    },
                    modifier = Modifier.size(44.dp)
                )
            }
        }
    }
}
