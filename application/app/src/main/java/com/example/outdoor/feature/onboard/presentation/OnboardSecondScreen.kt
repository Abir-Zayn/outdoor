package com.example.outdoor.feature.onboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.core.ui.typography.AppTextStyle
import com.example.outdoor.feature.onboard.presentation.components.OnboardNavigation
import com.example.outdoor.feature.onboard.presentation.components.OnboardPhoneHero
import com.example.outdoor.ui.theme.OutdoorBlack
import com.example.outdoor.ui.theme.OutdoorGray
import com.example.outdoor.ui.theme.OutdoorNearWhite
import com.example.outdoor.ui.theme.OutdoorTheme

@Composable
fun OnboardSecondScreen(
    onBack: () -> Unit = {},
    onNext: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OutdoorNearWhite)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.52f)
                    .background(Color(0xFFE6E6E6))
            ) {
                OnboardPhoneHero(modifier = Modifier.fillMaxSize())
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.48f)
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            AppTextStyle.HeadlineLarge.toSpanStyle().copy(color = OutdoorBlack)
                        ) {
                            append("Explore Nearby ")
                        }
                        withStyle(
                            AppTextStyle.HeadlineLarge.toSpanStyle().copy(
                                color = OutdoorGray,
                                fontStyle = FontStyle.Italic
                            )
                        ) {
                            append("Hotels\nThrough Interactive Map")
                        }
                    },
                    textAlign = TextAlign.Center
                )

                AppText.Body(
                    text = "Find top-rated hotels near you using an interactive map for easy browsing.",
                    textAlign = TextAlign.Center
                )

                OnboardNavigation(
                    currentPage = 1,
                    totalPages = 3,
                    onBack = onBack,
                    onNext = onNext,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        AppText.Label(
            text = "Skip",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 20.dp)
                .clickable { onSkip() }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnboardSecondScreenPreview() {
    OutdoorTheme {
        OnboardSecondScreen()
    }
}
