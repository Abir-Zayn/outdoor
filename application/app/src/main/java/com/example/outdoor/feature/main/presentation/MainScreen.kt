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
import com.example.outdoor.feature.roomdetailed.presentation.RoomDetailedScreen
import com.example.outdoor.feature.roomselection.presentation.RoomSelectionScreen
import com.example.outdoor.feature.roomselection.widgets.RoomUiModel

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    var showHotelDetail by remember { mutableStateOf(false) }
    var showRoomSelection by remember { mutableStateOf(false) }
    var showRoomDetailed by remember { mutableStateOf(false) }
    var selectedRoom by remember { mutableStateOf<RoomUiModel?>(null) }
    var pendingDetailRoom by remember { mutableStateOf<RoomUiModel?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            showRoomDetailed -> RoomDetailedScreen(
                onBackClick = { showRoomDetailed = false },
                onSelectRoomClick = {
                    selectedRoom = pendingDetailRoom
                    showRoomDetailed = false
                }
            )
            showRoomSelection -> RoomSelectionScreen(
                selectedRoom = selectedRoom,
                onCloseClick = { showRoomSelection = false },
                onViewDetailsClick = { room ->
                    pendingDetailRoom = room
                    showRoomDetailed = true
                },
                onRemoveRoom = { selectedRoom = null }
            )
            showHotelDetail -> HotelDetailedScreen(
                onBackClick = { showHotelDetail = false },
                onSelectRoomClick = { showRoomSelection = true }
            )
            else -> when (selectedTab) {
                0 -> HomeScreen(
                    navBarPadding = 96.dp,
                    onHotelClick = { showHotelDetail = true }
                )
                1 -> BookingsScreen()
                2 -> ChatScreen()
                3 -> ProfileScreen()
            }
        }

        if (!showHotelDetail && !showRoomSelection && !showRoomDetailed) {
            BottomNavBar(
                selectedIndex = selectedTab,
                onItemSelected = { selectedTab = it },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}
