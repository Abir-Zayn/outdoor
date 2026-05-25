package com.example.outdoor.feature.onboard.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppButton
import com.example.outdoor.core.ui.components.AppText

@Composable
fun OnboardActions(
    onGetStarted: () -> Unit,
    onSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        AppButton(
            text = "Let's Get Started",
            onClick = onGetStarted
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppText.Label(text = "Already have an account? ")
            AppText.LabelAccent(
                text = "Sign In",
                modifier = Modifier.clickable { onSignIn() }
            )
        }
    }
}
