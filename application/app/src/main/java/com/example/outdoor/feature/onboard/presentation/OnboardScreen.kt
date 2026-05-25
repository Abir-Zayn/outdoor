package com.example.outdoor.feature.onboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.core.ui.typography.AppTextStyle
import com.example.outdoor.feature.onboard.presentation.components.HeroImage
import com.example.outdoor.feature.onboard.presentation.components.OnboardActions
import com.example.outdoor.ui.theme.OutdoorBlack
import com.example.outdoor.ui.theme.OutdoorNearWhite
import com.example.outdoor.ui.theme.OutdoorOrange
import com.example.outdoor.ui.theme.OutdoorTheme

@Composable
fun OnboardScreen(
    onGetStarted: () -> Unit = {},
    onSignIn: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OutdoorNearWhite)
    ) {
        HeroImage(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.52f)
                .background(Color(0xFFE6E6E6))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.48f)
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Mixed-color headline — AnnotatedString required, kept inline
            Text(
                text = buildAnnotatedString {
                    withStyle(AppTextStyle.HeadlineLarge.toSpanStyle().copy(color = OutdoorBlack)) {
                        append("Find your ")
                    }
                    withStyle(
                        AppTextStyle.HeadlineLarge.toSpanStyle().copy(
                            color = OutdoorOrange,
                            fontStyle = FontStyle.Normal
                        )
                    ) {
                        append("perfect stay\nanywhere")
                    }
                    withStyle(AppTextStyle.HeadlineLarge.toSpanStyle().copy(color = OutdoorBlack)) {
                        append(" in the world")
                    }
                },
                textAlign = TextAlign.Center
            )

            AppText.Body(
                text = "Find and reserve exceptional stays worldwide with a seamless and reliable booking experience.",
                textAlign = TextAlign.Center
            )

            OnboardActions(
                onGetStarted = onGetStarted,
                onSignIn = onSignIn,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnboardScreenPreview() {
    OutdoorTheme {
        OnboardScreen()
    }
}
