package com.example.outdoor.feature.onboard.presentation.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.ui.theme.OutdoorBlue

@Composable
fun HeroImage(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth()) {
        val context = LocalContext.current
        val heroBitmap = remember {
            BitmapFactory.decodeStream(context.assets.open("onboarding-image-one.jpg"))
                .asImageBitmap()
        }
        Image(
            bitmap = heroBitmap,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 76.dp, end = 18.dp)
                .clip(RoundedCornerShape(50))
                .background(OutdoorBlue)
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            AppText.Badge(text = "#Luxury")
        }
    }
}
