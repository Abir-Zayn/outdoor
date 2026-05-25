package com.example.outdoor.core.util

// Function to validate whether the given email address format is correct
fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
