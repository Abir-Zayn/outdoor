package com.example.outdoor.feature.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.outdoor.core.ui.components.AppButton
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.core.ui.components.AppTextField
import com.example.outdoor.core.ui.typography.AppTextStyle
import com.example.outdoor.core.util.isValidEmail
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.LightPrimary
import com.example.outdoor.ui.theme.LightTextSecondary
import com.example.outdoor.ui.theme.LightBackground
import com.example.outdoor.ui.theme.OutdoorTheme

data class ForgetPasswordState(
    val email: String = "",
    val emailError: String? = null
)

@Composable
fun ForgetPasswordScreen(
    onSendResetLink: (String) -> Unit = {},
    onBackToLoginClick: () -> Unit = {}
) {
    var state by remember { mutableStateOf(ForgetPasswordState()) }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTabletOrLandscape = maxWidth > 600.dp

        if (isTabletOrLandscape) {
            Row(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF78BEFF),
                                    LightPrimary
                                )
                            )
                        )
                        .padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(18.dp)
                    ) {
                        AppText.Headline(
                            text = "Reset Password",
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        AppText.Body(
                            text = "Recover access to your account by verifying your registered email.",
                            color = Color.White.copy(alpha = 0.92f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1.2f)
                        .fillMaxHeight()
                        .background(LightBackground)
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .widthIn(max = 460.dp)
                            .background(Color.White, shape = RoundedCornerShape(24.dp))
                            .padding(32.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ForgetPasswordFormContent(
                            state = state,
                            onStateChange = { state = it },
                            onSendResetLink = onSendResetLink,
                            onBackToLoginClick = onBackToLoginClick
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF78BEFF),
                                LightPrimary
                            )
                        )
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(120.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                        )
                        .padding(horizontal = 24.dp, vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    ForgetPasswordFormContent(
                        state = state,
                        onStateChange = { state = it },
                        onSendResetLink = onSendResetLink,
                        onBackToLoginClick = onBackToLoginClick
                    )
                }
            }
        }
    }
}

@Composable
private fun ForgetPasswordFormContent(
    state: ForgetPasswordState,
    onStateChange: (ForgetPasswordState) -> Unit,
    onSendResetLink: (String) -> Unit,
    onBackToLoginClick: () -> Unit
) {
    AppText.Headline(
        text = "Forgot Password?",
        textAlign = TextAlign.Center
    )

    AppText.Body(
        text = "Enter the email you used when creating your account.",
        textAlign = TextAlign.Center,
        color = LightTextSecondary
    )

    Spacer(modifier = Modifier.height(8.dp))

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppText.Label(text = "Email address")
        AppTextField(
            value = state.email,
            onValueChange = { onStateChange(state.copy(email = it, emailError = null)) },
            placeholder = "Enter your registered email",
            isError = state.emailError != null
        )
        if (state.emailError != null) {
            Text(
                text = state.emailError,
                color = Color.Red,
                style = AppTextStyle.Body.copy(fontSize = 12.sp),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    AppButton(
        text = "Send Reset Link",
        onClick = {
            when {
                state.email.isBlank() -> onStateChange(state.copy(emailError = "Email cannot be empty"))
                !isValidEmail(state.email) -> onStateChange(state.copy(emailError = "Invalid email format"))
                else -> onSendResetLink(state.email.trim())
            }
        },
        containerColor = LightTextPrimary
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = "Back to Login",
        color = LightPrimary,
        style = AppTextStyle.LabelSemiBold,
        modifier = Modifier.clickable { onBackToLoginClick() }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ForgetPasswordScreenPreview() {
    OutdoorTheme {
        ForgetPasswordScreen()
    }
}
