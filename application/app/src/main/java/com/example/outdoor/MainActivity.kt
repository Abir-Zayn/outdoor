package com.example.outdoor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.outdoor.feature.onboard.presentation.OnboardScreen
import com.example.outdoor.feature.onboard.presentation.OnboardSecondScreen
import com.example.outdoor.feature.auth.presentation.LoginScreen
import com.example.outdoor.ui.theme.OutdoorNearWhite
import com.example.outdoor.ui.theme.OutdoorTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OutdoorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = OutdoorNearWhite
                ) {
                    var currentScreen by remember { mutableIntStateOf(0) }

                    when (currentScreen) {
                        0 -> OnboardScreen(
                            onGetStarted = { currentScreen = 1 },
                            onSignIn = { currentScreen = 2 }
                        )
                        1 -> OnboardSecondScreen(
                            onBack = { currentScreen = 0 },
                            onNext = { currentScreen = 2 },
                            onSkip = { currentScreen = 2 }
                        )
                        2 -> LoginScreen(
                            onLoginSuccess = { currentScreen = 0 },
                            onForgotPasswordClick = { /* Handle forgot password */ },
                            onSignUpClick = { /* Handle sign up redirection */ },
                            onSocialLoginClick = { /* Handle social login */ }
                        )
                    }
                }
            }
        }
    }
}
