package com.example.outdoor.feature.roomdetailed.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.outdoor.feature.detailed.presentation.RoomImageUiModel
import com.example.outdoor.feature.detailed.presentation.components.SelectRoomComponent
import com.example.outdoor.feature.roomdetailed.presentation.components.RoomAmenitiesList
import com.example.outdoor.feature.roomdetailed.presentation.components.RoomDetailedTopBar
import com.example.outdoor.feature.roomdetailed.presentation.components.RoomImageCarousel
import com.example.outdoor.feature.roomdetailed.presentation.components.RoomSummary
import com.example.outdoor.ui.theme.OutdoorTheme

data class RoomDetailUiModel(
    val title: String,
    val bedSize: String,
    val guestCount: Int,
    val roomType: String,
    val characteristics: String,
    val smokingPolicy: String,
    val roomSizeSqft: Int,
    val images: List<RoomImageUiModel>,
    val amenities: List<String>,
    val pricePerNight: Int
)

private val sampleRoom = RoomDetailUiModel(
    title = "Deluxe Double Room",
    bedSize = "King Bed",
    guestCount = 2,
    roomType = "Double",
    characteristics = "City View",
    smokingPolicy = "Non-smoking / Smoking-zoned",
    roomSizeSqft = 500,
    images = listOf(
        RoomImageUiModel("Deluxe Double Room", Color(0xFF5C8FB8)),
        RoomImageUiModel("City View Balcony", Color(0xFF7FA889)),
        RoomImageUiModel("Bathroom Suite", Color(0xFFB58B6F))
    ),
    amenities = listOf(
        "Free Wi-Fi", "Air conditioning", "Flat-screen TV",
        "Mini bar", "Safe", "Hair dryer", "Coffee maker",
        "Room service", "Daily housekeeping", "Bathrobe & slippers"
    ),
    pricePerNight = 1200
)

@Composable
fun RoomDetailedScreen(
    room: RoomDetailUiModel = sampleRoom,
    onBackClick: () -> Unit = {},
    onSelectRoomClick: (RoomDetailUiModel) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            RoomDetailedTopBar(onBackClick = onBackClick)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentPadding = PaddingValues(bottom = 120.dp)
            ) {
                item {
                    RoomImageCarousel(images = room.images)
                }

                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 22.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        RoomSummary(
                            title = room.title,
                            bedSize = room.bedSize,
                            guestCount = room.guestCount,
                            roomType = room.roomType,
                            characteristics = room.characteristics,
                            smokingPolicy = room.smokingPolicy,
                            roomSizeSqft = room.roomSizeSqft
                        )

                        RoomAmenitiesList(amenities = room.amenities)
                    }
                }
            }
        }

        SelectRoomComponent(
            priceText = "BDT ${room.pricePerNight}tk",
            nightText = "for 1 night",
            onSelectRoomClick = { onSelectRoomClick(room) },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun RoomDetailedScreenPreview() {
    OutdoorTheme {
        RoomDetailedScreen()
    }
}
