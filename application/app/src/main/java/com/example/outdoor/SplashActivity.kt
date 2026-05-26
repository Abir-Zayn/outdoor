package com.example.outdoor

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
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
import androidx.compose.runtime.mutableIntStateOf
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
    val brandText = "Outdoor."
    var visibleChars by remember { mutableIntStateOf(0) }
    var showLogo by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val totalAnimationMillis = 1200L
        val logoAnimationMillis = 300L
        val perCharDelay = (totalAnimationMillis - logoAnimationMillis) / brandText.length

        repeat(brandText.length) { index ->
            delay(perCharDelay)
            visibleChars = index + 1
        }

        showLogo = true
        delay(logoAnimationMillis)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row {
                brandText.forEachIndexed { index, char ->
                    AnimatedVisibility(
                        visible = index < visibleChars,
                        enter = slideInHorizontally(
                            animationSpec = tween(durationMillis = 220),
                            initialOffsetX = { -it / 2 }
                        ) + fadeIn(animationSpec = tween(durationMillis = 220))
                    ) {
                        Text(
                            text = char.toString(),
                            color = OutdoorBlue,
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = IBMPlexSerif,
                            letterSpacing = 0.02.sp
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = showLogo,
                enter = fadeIn(animationSpec = tween(durationMillis = 300)) +
                    scaleIn(animationSpec = tween(durationMillis = 300), initialScale = 0.85f)
            ) {
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
