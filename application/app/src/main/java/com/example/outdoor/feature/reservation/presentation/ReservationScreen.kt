package com.example.outdoor.feature.reservation.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppButton
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.feature.reservation.presentation.components.ReservationDateComponent
import com.example.outdoor.feature.reservation.presentation.components.ReservationHotelCard
import com.example.outdoor.feature.reservation.presentation.components.ReservationTopBar
import com.example.outdoor.feature.reservation.presentation.components.RoomsGuestsBottomSheet
import com.example.outdoor.feature.reservation.presentation.components.RoomsGuestsField
import com.example.outdoor.ui.theme.LightTextPrimary
import com.example.outdoor.ui.theme.OutdoorTheme
import java.util.Calendar

private val sampleHotel = ReservationHotelUiModel(
    name = "Palm Haven Retreat",
    location = "120 Park Avenue, New York..",
    rating = 4.9,
    accentColor = Color(0xFF5C8FB8)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationScreen(
    hotel: ReservationHotelUiModel = sampleHotel,
    onBackClick: () -> Unit = {},
    onContinueClick: (checkIn: Int?, checkOut: Int?, guests: GuestSelection) -> Unit = { _, _, _ -> }
) {
    val displayedMonth = remember {
        Calendar.getInstance().apply { set(2026, Calendar.MAY, 1) }
    }
    // Trigger recomposition on month change without mutating the same Calendar identity
    var monthTick by remember { mutableIntStateOf(0) }

    var checkIn by remember { mutableStateOf<Int?>(11) }
    var checkOut by remember { mutableStateOf<Int?>(14) }
    var guests by remember { mutableStateOf(GuestSelection()) }
    var showGuestSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(modifier = Modifier.fillMaxSize()) {
            ReservationTopBar(onBackClick = onBackClick)

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ReservationHotelCard(hotel = hotel)

                AppText.SubHeadline(text = "Select Date", color = LightTextPrimary)

                key(monthTick) {
                    ReservationDateComponent(
                        displayedMonth = displayedMonth,
                        checkIn = checkIn,
                        checkOut = checkOut,
                        onPrevMonth = {
                            displayedMonth.add(Calendar.MONTH, -1)
                            checkIn = null
                            checkOut = null
                            monthTick++
                        },
                        onNextMonth = {
                            displayedMonth.add(Calendar.MONTH, 1)
                            checkIn = null
                            checkOut = null
                            monthTick++
                        },
                        onDayClick = { day ->
                            val start = checkIn
                            when {
                                start == null || checkOut != null -> {
                                    checkIn = day
                                    checkOut = null
                                }
                                day > start -> checkOut = day
                                else -> checkIn = day
                            }
                        }
                    )
                }

                RoomsGuestsField(
                    guests = guests,
                    onClick = { showGuestSheet = true }
                )
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation = 12.dp
            ) {
                AppButton(
                    text = "Continue",
                    onClick = { onContinueClick(checkIn, checkOut, guests) },
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(horizontal = 20.dp, vertical = 14.dp)
                )
            }
        }
    }

    if (showGuestSheet) {
        RoomsGuestsBottomSheet(
            initial = guests,
            sheetState = sheetState,
            onDismiss = { showGuestSheet = false },
            onConfirm = {
                guests = it
                showGuestSheet = false
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ReservationScreenPreview() {
    OutdoorTheme {
        ReservationScreen()
    }
}
