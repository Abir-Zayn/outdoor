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
import com.example.outdoor.core.util.isValidEmail
import com.example.outdoor.ui.theme.OutdoorBlack
import com.example.outdoor.ui.theme.OutdoorBlue
import com.example.outdoor.ui.theme.OutdoorGray
import com.example.outdoor.ui.theme.OutdoorLightGray
import com.example.outdoor.ui.theme.OutdoorNearWhite
import com.example.outdoor.ui.theme.OutdoorTheme

data class RegisterState(
    val fullName: String = "",
    val age: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val fullNameError: String? = null,
    val ageError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null
)

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit = {},
    onSignInClick: () -> Unit = {},
    onSocialSignUpClick: (String) -> Unit = {}
) {
    var state by remember { mutableStateOf(RegisterState()) }

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
                                    OutdoorBlue
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
                            text = "Create Account",
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        AppText.Body(
                            text = "Sign up and start discovering exceptional outdoor stays.",
                            color = Color.White.copy(alpha = 0.92f),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1.2f)
                        .fillMaxHeight()
                        .background(OutdoorNearWhite)
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
                        RegisterFormContent(
                            state = state,
                            onStateChange = { state = it },
                            onRegisterSuccess = onRegisterSuccess,
                            onSignInClick = onSignInClick,
                            onSocialSignUpClick = onSocialSignUpClick
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
                                OutdoorBlue
                            )
                        )
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(86.dp))

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
                    RegisterFormContent(
                        state = state,
                        onStateChange = { state = it },
                        onRegisterSuccess = onRegisterSuccess,
                        onSignInClick = onSignInClick,
                        onSocialSignUpClick = onSocialSignUpClick
                    )
                }
            }
        }
    }
}

@Composable
private fun RegisterFormContent(
    state: RegisterState,
    onStateChange: (RegisterState) -> Unit,
    onRegisterSuccess: () -> Unit,
    onSignInClick: () -> Unit,
    onSocialSignUpClick: (String) -> Unit
) {
    AppText.Headline(
        text = "Create Account",
        textAlign = TextAlign.Center
    )

    AppText.Body(
        text = "Fill up this form to create a new\naccount",
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(8.dp))

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppText.Label(text = "Full Name")
        AppTextField(
            value = state.fullName,
            onValueChange = { onStateChange(state.copy(fullName = it, fullNameError = null)) },
            placeholder = "Enter your full name",
            isError = state.fullNameError != null
        )
        if (state.fullNameError != null) {
            Text(
                text = state.fullNameError,
                color = Color.Red,
                style = AppTextStyle.Body.copy(fontSize = 12.sp),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppText.Label(text = "Age")
        AppTextField(
            value = state.age,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    onStateChange(state.copy(age = newValue, ageError = null))
                }
            },
            placeholder = "Enter your age",
            isError = state.ageError != null,
            modifier = Modifier,
            visualTransformation = VisualTransformation.None
        )
        if (state.ageError != null) {
            Text(
                text = state.ageError,
                color = Color.Red,
                style = AppTextStyle.Body.copy(fontSize = 12.sp),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AppText.Label(text = "Email address")
        AppTextField(
            value = state.email,
            onValueChange = { onStateChange(state.copy(email = it, emailError = null)) },
            placeholder = "Enter your email address",
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
                text = state.passwordError,
                color = Color.Red,
                style = AppTextStyle.Body.copy(fontSize = 12.sp),
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(4.dp))

    AppButton(
        text = "Sign Up",
        onClick = {
            val ageValue = state.age.toIntOrNull()
            val trimmedName = state.fullName.trim()

            when {
                trimmedName.isEmpty() -> onStateChange(state.copy(fullNameError = "Full name cannot be empty"))
                ageValue == null -> onStateChange(state.copy(ageError = "Age must be a number"))
                ageValue < 13 -> onStateChange(state.copy(ageError = "Age must be at least 13"))
                state.email.isEmpty() -> onStateChange(state.copy(emailError = "Email cannot be empty"))
                !isValidEmail(state.email) -> onStateChange(state.copy(emailError = "Invalid email format"))
                state.password.isEmpty() -> onStateChange(state.copy(passwordError = "Password cannot be empty"))
                state.password.length < 6 -> onStateChange(state.copy(passwordError = "Password must be at least 6 characters"))
                else -> onRegisterSuccess()
            }
        },
        containerColor = OutdoorBlack
    )

    Spacer(modifier = Modifier.height(4.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = OutdoorLightGray)
        AppText.Body(text = "Or Sign up With", color = OutdoorGray)
        HorizontalDivider(modifier = Modifier.weight(1f), color = OutdoorLightGray)
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialIconButton(onClick = { onSocialSignUpClick("facebook") }) {
            FacebookIcon()
        }
        SocialIconButton(onClick = { onSocialSignUpClick("google") }) {
            GoogleIcon()
        }
        SocialIconButton(onClick = { onSocialSignUpClick("apple") }) {
            AppleIcon()
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText.Body(text = "Already have an account?", color = OutdoorGray)
        Text(
            text = "Sign in",
            color = OutdoorBlue,
            style = AppTextStyle.LabelSemiBold,
            modifier = Modifier.clickable { onSignInClick() }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RegisterScreenPreview() {
    OutdoorTheme {
        RegisterScreen()
    }
}
