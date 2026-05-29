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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.outdoor.core.ui.components.AppButton
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.core.ui.components.AppTextField
import com.example.outdoor.core.ui.components.AppleIcon
import com.example.outdoor.core.ui.components.FacebookIcon
import com.example.outdoor.core.ui.components.GoogleIcon
import com.example.outdoor.core.ui.components.PasswordVisibilityIcon
import com.example.outdoor.core.ui.components.SocialIconButton
import com.example.outdoor.core.ui.typography.AppTextStyle
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.LightPrimary
import com.example.outdoor.ui.theme.LightTextSecondary
import com.example.outdoor.ui.theme.LightSecondaryBackground
import com.example.outdoor.ui.theme.LightBackground
import com.example.outdoor.ui.theme.OutdoorTheme

/**
 * Clean Architecture Component state representation for the Login screen UI.
 */
data class LoginState(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null
)

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onSocialLoginClick: (String) -> Unit = {}
) {
    var state by remember { mutableStateOf(LoginState()) }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTabletOrLandscape = maxWidth > 600.dp

        if (isTabletOrLandscape) {
            // Adaptive 2-pane layout for wide screens, foldables, and landscape orientations
            Row(modifier = Modifier.fillMaxSize()) {
                // Left pane: branding and motivational summary with beautiful blue gradient
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF589EFF),
                                    LightPrimary
                                )
                            )
                        )
                        .padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        AppText.Headline(
                            text = "Outdoor Stay",
                            color = Color.White
                        )
                        AppText.Body(
                            text = "Find and reserve exceptional stays worldwide with a seamless and reliable booking experience.",
                            color = Color.White.copy(alpha = 0.9f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Right pane: white form container floating on a subtle neutral background
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
                        LoginFormContent(
                            state = state,
                            onStateChange = { state = it },
                            onLoginSuccess = onLoginSuccess,
                            onForgotPasswordClick = onForgotPasswordClick,
                            onSignUpClick = onSignUpClick,
                            onSocialLoginClick = onSocialLoginClick
                        )
                    }
                }
            }
        } else {
            // Stacked vertical layout for portrait phones and smaller displays
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF589EFF),
                                LightPrimary
                            )
                        )
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(180.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                        )
                        .padding(horizontal = 24.dp, vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    LoginFormContent(
                        state = state,
                        onStateChange = { state = it },
                        onLoginSuccess = onLoginSuccess,
                        onForgotPasswordClick = onForgotPasswordClick,
                        onSignUpClick = onSignUpClick,
                        onSocialLoginClick = onSocialLoginClick
                    )
                }
            }
        }
    }
}

@Composable
fun LoginFormContent(
    state: LoginState,
    onStateChange: (LoginState) -> Unit,
    onLoginSuccess: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onSocialLoginClick: (String) -> Unit
) {
    // Headline & subtitle using custom AppText variants
    AppText.Headline(
        text = "Welcome Back",
        textAlign = TextAlign.Center
    )

    AppText.Body(
        text = "Enter your details below to log back into your account",
        textAlign = TextAlign.Center,
        color = LightTextSecondary
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Email Input section
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppText.Label(text = "Email address")
        AppTextField(
            value = state.email,
            onValueChange = { onStateChange(state.copy(email = it, emailError = null)) },
            placeholder = "harrybender@gmail.com",
            isError = state.emailError != null
        )
        if (state.emailError != null) {
            Text(
                text = state.emailError ?: "",
                color = Color.Red,
                style = AppTextStyle.Body.copy(fontSize = 12.sp),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }

    // Password Input section
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppText.Label(text = "Password")
        AppTextField(
            value = state.password,
            onValueChange = { onStateChange(state.copy(password = it, passwordError = null)) },
            placeholder = "********",
            visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = state.passwordError != null,
            trailingIcon = {
                IconButton(onClick = { onStateChange(state.copy(isPasswordVisible = !state.isPasswordVisible)) }) {
                    PasswordVisibilityIcon(isVisible = state.isPasswordVisible)
                }
            }
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError ?: "",
                color = Color.Red,
                style = AppTextStyle.Body.copy(fontSize = 12.sp),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }

    // Remember me & Forgot password Row
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Checkbox(
                checked = state.rememberMe,
                onCheckedChange = { onStateChange(state.copy(rememberMe = it)) },
                colors = CheckboxDefaults.colors(
                    checkedColor = LightPrimary,
                    uncheckedColor = LightTextSecondary
                )
            )
            AppText.Label(text = "Remember me", color = LightTextSecondary)
        }

        Text(
            text = "Forgot password?",
            color = LightPrimary,
            style = AppTextStyle.LabelSemiBold,
            modifier = Modifier.clickable { onForgotPasswordClick() }
        )
    }

    Spacer(modifier = Modifier.height(4.dp))

    // Log in button
    AppButton(
        text = "Log in",
        onClick = {
            onStateChange(state.copy(emailError = null, passwordError = null))
            onLoginSuccess()
        },
        containerColor = LightTextPrimary
    )

    Spacer(modifier = Modifier.height(4.dp))

    // Social log in divider
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = LightSecondaryBackground)
        AppText.Body(text = "Or Log In With", color = LightTextSecondary)
        HorizontalDivider(modifier = Modifier.weight(1f), color = LightSecondaryBackground)
    }

    // Social buttons: Facebook, Google, Apple
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialIconButton(onClick = { onSocialLoginClick("facebook") }) {
            FacebookIcon()
        }
        SocialIconButton(onClick = { onSocialLoginClick("google") }) {
            GoogleIcon()
        }
        SocialIconButton(onClick = { onSocialLoginClick("apple") }) {
            AppleIcon()
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Sign up redirection
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText.Body(text = "Don't have an account?", color = LightTextSecondary)
        Text(
            text = "Sign Up",
            color = LightPrimary,
            style = AppTextStyle.LabelSemiBold,
            modifier = Modifier.clickable { onSignUpClick() }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    OutdoorTheme {
        LoginScreen()
    }
}
