package com.example.outdoor.feature.roomselection.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.outdoor.feature.roomselection.widgets.CheckoutRoomComponent
import com.example.outdoor.feature.roomselection.widgets.HotelRoomCard
import com.example.outdoor.feature.roomselection.widgets.RoomSelectionTopBar
import com.example.outdoor.feature.roomselection.widgets.RoomUiModel
import com.example.outdoor.feature.roomselection.widgets.SelectedRoomDialog
import com.example.outdoor.ui.theme.OutdoorTheme

private val sampleRooms = listOf(
    RoomUiModel(1, "Ocean View Suite Sea View", "King bed", 2, Color(0xFF5C8FB8), pricePerNight = 1500),
    RoomUiModel(2, "Deluxe King Room Side Sea View", "King bed", 2, Color(0xFF7FA889), pricePerNight = 1200),
    RoomUiModel(3, "Family Twin Room", "2 Twin beds", 4, Color(0xFFB58B6F), pricePerNight = 1800),
    RoomUiModel(4, "Poolside Studio Execlusive", "Queen bed", 3, Color(0xFF9B7FB5), pricePerNight = 1100)
)

@Composable
fun RoomSelectionScreen(
    rooms: List<RoomUiModel> = sampleRooms,
    dateRange: String = "12 Jun - 15 Jun",
    roomCount: Int = 1,
    guestCount: Int = 2,
    selectedRoom: RoomUiModel? = null,
    onCloseClick: () -> Unit = {},
    onRoomClick: (RoomUiModel) -> Unit = {},
    onViewDetailsClick: (RoomUiModel) -> Unit = {},
    onRemoveRoom: () -> Unit = {},
    onBookClick: () -> Unit = {}
) {
    var showSelectedDialog by remember { mutableStateOf(false) }

    if (showSelectedDialog && selectedRoom != null) {
        SelectedRoomDialog(
            room = selectedRoom,
            onClose = { showSelectedDialog = false },
            onRemove = {
                showSelectedDialog = false
                onRemoveRoom()
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                RoomSelectionTopBar(
                    dateRange = dateRange,
                    roomCount = roomCount,
                    guestCount = guestCount,
                    onCloseClick = onCloseClick
                )
            }

            items(rooms, key = { it.id }) { room ->
                HotelRoomCard(
                    room = room,
                    onClick = onRoomClick,
                    onViewDetails = onViewDetailsClick,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }

        CheckoutRoomComponent(
            selectedRoom = selectedRoom,
            onViewSelectedClick = { showSelectedDialog = true },
            onProceedClick = onBookClick,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RoomSelectionScreenPreview() {
    OutdoorTheme {
        RoomSelectionScreen()
    }
}
