package com.example.outdoor.feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppButton
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.core.ui.components.AppTextField
import com.example.outdoor.feature.home.presentation.components.Hotel
import com.example.outdoor.feature.home.presentation.components.HotelCard
import com.example.outdoor.ui.theme.OutdoorBlue
import com.example.outdoor.ui.theme.OutdoorTheme

private val mostPopularHotels = listOf(
    Hotel(1, "The Grand Orchid Resort", 199, 4.5f, Color(0xFF8BA5B5)),
    Hotel(2, "The Prestige Villa", 35, 4.6f, Color(0xFFB59AB5)),
    Hotel(3, "Ocean View Suites", 249, 4.8f, Color(0xFF7BA8A5))
)

private val trendingHotels = listOf(
    Hotel(4, "Palm Paradise Hotel", 120, 4.5f, Color(0xFF7BA89A)),
    Hotel(5, "City Center Plaza", 89, 4.5f, Color(0xFFB5A57B)),
    Hotel(6, "Mountain Escape Lodge", 175, 4.7f, Color(0xFF9AB5A5))
)

private val fieldContainerColor = Color.White.copy(alpha = 0.10f)
private val fieldPlaceholderColor = Color.White.copy(alpha = 0.70f)

@Composable
fun HomeScreen(
    onNotificationClick: () -> Unit = {},
    onFindClick: (destination: String, dates: String, guests: String) -> Unit = { _, _, _ -> }
) {
    var destination by remember { mutableStateOf("") }
    var dates by remember { mutableStateOf("") }
    var guests by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Blue gradient header — search area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF589EFF), OutdoorBlue)
                    )
                )
                .statusBarsPadding()
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp, bottom = 28.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                // Greeting + notification bell
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppText.Body(text = "Hey, Harry Bender", color = Color.White)

                    BadgedBox(
                        badge = {
                            Badge(containerColor = Color.Red)
                        }
                    ) {
                        IconButton(
                            onClick = onNotificationClick,
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color.White.copy(alpha = 0.15f), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "Notifications",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                // Headline
                AppText.Headline(
                    text = "Where will you check in next?",
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Destination field
                AppTextField(
                    value = destination,
                    onValueChange = { destination = it },
                    placeholder = "Enter Destination",
                    containerColor = fieldContainerColor,
                    textColor = Color.White,
                    placeholderColor = fieldPlaceholderColor,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null,
                            tint = fieldPlaceholderColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                )

                // Check-in / Guest row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    AppTextField(
                        value = dates,
                        onValueChange = { dates = it },
                        placeholder = "Check In - Check out",
                        modifier = Modifier.weight(1.6f),
                        containerColor = fieldContainerColor,
                        textColor = Color.White,
                        placeholderColor = fieldPlaceholderColor,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = null,
                                tint = fieldPlaceholderColor,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                    AppTextField(
                        value = guests,
                        onValueChange = { guests = it },
                        placeholder = "Guest",
                        modifier = Modifier.weight(1f),
                        containerColor = fieldContainerColor,
                        textColor = Color.White,
                        placeholderColor = fieldPlaceholderColor,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = null,
                                tint = fieldPlaceholderColor,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                }

                // Find button — white pill with blue text + search icon
                AppButton(
                    text = "Find",
                    onClick = { onFindClick(destination, dates, guests) },
                    containerColor = Color.White,
                    textColor = OutdoorBlue,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            tint = OutdoorBlue,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                )
            }
        }

        // White content section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 24.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Most Popular
            AppText.SubHeadline(
                text = "Most Popular",
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(mostPopularHotels) { hotel ->
                    HotelCard(hotel = hotel)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Trending Hotels
            AppText.SubHeadline(
                text = "Trending Hotels",
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(trendingHotels) { hotel ->
                    HotelCard(hotel = hotel)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    OutdoorTheme {
        HomeScreen()
    }
}
