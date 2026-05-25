package com.example.outdoor.feature.auth.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.outdoor.core.ui.components.AppButton
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.core.ui.typography.AppTextStyle
import com.example.outdoor.ui.theme.OutdoorBlack
import com.example.outdoor.ui.theme.OutdoorBlue
import com.example.outdoor.ui.theme.OutdoorGray
import com.example.outdoor.ui.theme.OutdoorLightGray
import com.example.outdoor.ui.theme.OutdoorNearWhite
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

// Function to validate whether the given email address format is correct
fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onSocialLoginClick: (String) -> Unit = {}
) {
    var state by remember { mutableStateOf(LoginState()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF589EFF),
                        OutdoorBlue
                    )
                )
            )
    ) {
        // Scrollable content wrapper to handle small screens or keyboard overlays gracefully
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Top background spacing to replicate the upper blue gradient header
            Spacer(modifier = Modifier.height(180.dp))

            // White login card containing the form elements
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Headline & subtitle using custom AppText variants
                AppText.Headline(
                    text = "Welcome Back",
                    textAlign = TextAlign.Center
                )

                AppText.Body(
                    text = "Enter your details below to log back into your account",
                    textAlign = TextAlign.Center,
                    color = OutdoorGray
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Email Input section
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppText.Label(text = "Email address")
                    LoginTextField(
                        value = state.email,
                        onValueChange = { state = state.copy(email = it, emailError = null) },
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
                    LoginTextField(
                        value = state.password,
                        onValueChange = { state = state.copy(password = it, passwordError = null) },
                        placeholder = "********",
                        visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        isError = state.passwordError != null,
                        trailingIcon = {
                            IconButton(onClick = { state = state.copy(isPasswordVisible = !state.isPasswordVisible) }) {
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
                            onCheckedChange = { state = state.copy(rememberMe = it) },
                            colors = CheckboxDefaults.colors(
                                checkedColor = OutdoorBlue,
                                uncheckedColor = OutdoorGray
                            )
                        )
                        AppText.Label(text = "Remember me", color = OutdoorGray)
                    }

                    Text(
                        text = "Forgot password?",
                        color = OutdoorBlue,
                        style = AppTextStyle.LabelSemiBold,
                        modifier = Modifier.clickable { onForgotPasswordClick() }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Log in button
                AppButton(
                    text = "Log in",
                    onClick = {
                        // Calling isValidEmail function to verify the user input before executing login
                        val isEmailValid = isValidEmail(state.email)

                        if (state.email.isEmpty()) {
                            state = state.copy(emailError = "Email cannot be empty")
                        } else if (!isEmailValid) {
                            state = state.copy(emailError = "Invalid email format")
                        } else if (state.password.isEmpty()) {
                            state = state.copy(passwordError = "Password cannot be empty")
                        } else if (state.password.length < 6) {
                            state = state.copy(passwordError = "Password must be at least 6 characters")
                        } else {
                            onLoginSuccess()
                        }
                    },
                    containerColor = OutdoorBlack
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Social log in divider
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f), color = OutdoorLightGray)
                    AppText.Body(text = "Or Log In With", color = OutdoorGray)
                    HorizontalDivider(modifier = Modifier.weight(1f), color = OutdoorLightGray)
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

                Spacer(modifier = Modifier.height(12.dp))

                // Sign up redirection
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppText.Body(text = "Don't have an account?", color = OutdoorGray)
                    Text(
                        text = "Sign Up",
                        color = OutdoorBlue,
                        style = AppTextStyle.LabelSemiBold,
                        modifier = Modifier.clickable { onSignUpClick() }
                    )
                }
            }
        }
    }
}

@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = if (isError) 1.dp else 0.dp,
                color = if (isError) Color.Red else Color.Transparent,
                shape = RoundedCornerShape(28.dp)
            ),
        placeholder = { Text(text = placeholder, color = OutdoorGray, style = AppTextStyle.Body) },
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(28.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = OutdoorNearWhite,
            unfocusedContainerColor = OutdoorNearWhite,
            disabledContainerColor = OutdoorNearWhite,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            cursorColor = OutdoorBlue
        )
    )
}

@Composable
fun SocialIconButton(
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun PasswordVisibilityIcon(isVisible: Boolean) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val width = size.width
        val height = size.height
        val strokeWidth = 2.dp.toPx()
        val color = OutdoorGray

        if (isVisible) {
            // Draw open eye arc
            drawArc(
                color = color,
                startAngle = 10f,
                sweepAngle = 160f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
            )
            drawArc(
                color = color,
                startAngle = 190f,
                sweepAngle = 160f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
            )
            drawCircle(
                color = color,
                radius = 4.dp.toPx()
            )
        } else {
            // Draw closed/slash eye arc
            drawArc(
                color = color,
                startAngle = 10f,
                sweepAngle = 160f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
            )
            drawArc(
                color = color,
                startAngle = 190f,
                sweepAngle = 160f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
            )
            drawCircle(
                color = color,
                radius = 4.dp.toPx()
            )
            // Slash line
            drawLine(
                color = color,
                start = Offset(4.dp.toPx(), 4.dp.toPx()),
                end = Offset(20.dp.toPx(), 20.dp.toPx()),
                strokeWidth = strokeWidth
            )
        }
    }
}

@Composable
fun FacebookIcon() {
    Box(
        modifier = Modifier
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
fun GoogleIcon() {
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(Color.White, shape = CircleShape)
            .border(1.dp, OutdoorLightGray, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(20.dp)) {
            val width = size.width
            val height = size.height
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
fun AppleIcon() {
    Box(
        modifier = Modifier
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    OutdoorTheme {
        LoginScreen()
    }
}
