package com.example.outdoor.feature.main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.BottomNavBar
import com.example.outdoor.feature.bookings.presentation.BookingsScreen
import com.example.outdoor.feature.chat.presentation.ChatScreen
import com.example.outdoor.feature.detailed.presentation.HotelDetailedScreen
import com.example.outdoor.feature.home.presentation.HomeScreen
import com.example.outdoor.feature.profile.presentation.ProfileScreen

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    var showHotelDetail by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        if (showHotelDetail) {
            HotelDetailedScreen(
                onBackClick = { showHotelDetail = false }
            )
        } else {
            when (selectedTab) {
                0 -> HomeScreen(
                    navBarPadding = 96.dp,
                    onHotelClick = { showHotelDetail = true }
                )
                1 -> BookingsScreen()
                2 -> ChatScreen()
                3 -> ProfileScreen()
            }
        }

        if (!showHotelDetail) {
            BottomNavBar(
                selectedIndex = selectedTab,
                onItemSelected = { selectedTab = it },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
