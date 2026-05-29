package com.example.outdoor.feature.detailed.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.feature.detailed.presentation.components.AmenitiesSection
import com.example.outdoor.ui.theme.OutdoorBlack
import com.example.outdoor.ui.theme.OutdoorBlue
import com.example.outdoor.ui.theme.OutdoorGold
import com.example.outdoor.ui.theme.OutdoorGray
import com.example.outdoor.ui.theme.OutdoorNearWhite
import com.example.outdoor.ui.theme.OutdoorOrange
import com.example.outdoor.ui.theme.OutdoorTheme

data class HotelDetailUiModel(
    val name: String,
    val location: String,
    val reviewAverage: Float,
    val totalReviews: Int,
    val checkInTime: String,
    val checkOutTime: String,
    val description: String,
    val roomImages: List<RoomImageUiModel>,
    val amenities: List<String>,
    val nearbyActivities: List<String>,
    val housePolicies: List<String>,
    val childPolicies: List<String>,
    val petPolicies: List<String>,
    val digitalPaymentOptions: List<String>
)

data class RoomImageUiModel(
    val title: String,
    val accentColor: Color
)

private val sampleHotelDetail = HotelDetailUiModel(
    name = "The Grand Orchid Resort",
    location = "Cox's Bazar Sea Beach, Bangladesh",
    reviewAverage = 4.8f,
    totalReviews = 1284,
    checkInTime = "02:00 PM",
    checkOutTime = "11:00 AM",
    description = "A calm beachside stay with spacious rooms, ocean-facing balconies, and easy access to dining, shopping, and local tour points.",
    roomImages = listOf(
        RoomImageUiModel("Ocean View Suite", Color(0xFF5C8FB8)),
        RoomImageUiModel("Deluxe King Room", Color(0xFF7FA889)),
        RoomImageUiModel("Poolside Lounge", Color(0xFFB58B6F))
    ),
    amenities = listOf("Free Wi-Fi", "Pool", "Breakfast", "Parking", "Air conditioning", "Room service"),
    nearbyActivities = listOf("Beach walk", "Sunset point", "Local seafood market", "Boat tour desk"),
    housePolicies = listOf("Photo ID required at check-in", "No smoking inside rooms", "Quiet hours after 10:00 PM"),
    childPolicies = listOf("Children are welcome", "Extra bed available on request", "Kids under 6 stay free with parents"),
    petPolicies = listOf("Pets are not allowed", "Service animals accepted with prior notice"),
    digitalPaymentOptions = listOf("Visa", "Mastercard", "Mobile wallet", "Online banking")
)

@Composable
fun HotelDetailedScreen(
    hotel: HotelDetailUiModel = sampleHotelDetail,
    onBackClick: () -> Unit = {},
    onShareClick: () -> Unit = {}
) {
    var isFavorite by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            HotelImageCarousel(
                images = hotel.roomImages,
                isFavorite = isFavorite,
                onFavoriteClick = { isFavorite = !isFavorite },
                onBackClick = onBackClick,
                onShareClick = onShareClick
            )
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 22.dp),
                verticalArrangement = Arrangement.spacedBy(22.dp)
            ) {
                HotelHeader(hotel = hotel)

                DetailSection(title = "Amenities") {
                    AmenitiesSection(amenities = hotel.amenities)
                }

                CheckTimesRow(
                    checkInTime = hotel.checkInTime,
                    checkOutTime = hotel.checkOutTime
                )

                DetailSection(title = "Description") {
                    AppText.Body(text = hotel.description, color = OutdoorGray)
                }

                DetailSection(title = "Location") {
                    MapPlaceholder(location = hotel.location)
                }

                DetailSection(title = "Nearby Activities") {
                    BulletList(items = hotel.nearbyActivities, icon = Icons.Filled.LocationOn)
                }

                DetailSection(title = "House Policy") {
                    BulletList(items = hotel.housePolicies, icon = Icons.Filled.Info)
                }

                DetailSection(title = "Child Policy") {
                    BulletList(items = hotel.childPolicies, icon = Icons.Filled.CheckCircle)
                }

                DetailSection(title = "Pet Policy") {
                    BulletList(items = hotel.petPolicies, icon = Icons.Filled.Info)
                }

                DetailSection(title = "What People Say about this place?") {
                    ReviewSummary(hotel = hotel)
                }

                DetailSection(title = "Property accepts") {
                    BulletList(items = hotel.digitalPaymentOptions, icon = Icons.Filled.CreditCard)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HotelImageCarousel(
    images: List<RoomImageUiModel>,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(310.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val image = images[page]
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                image.accentColor.copy(alpha = 0.85f),
                                image.accentColor,
                                OutdoorBlack.copy(alpha = 0.78f)
                            )
                        )
                    )
            ) {
                AppText.Headline(
                    text = image.title,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 20.dp, end = 20.dp, bottom = 44.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ImageActionButton(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                onClick = onBackClick
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ImageActionButton(
                    icon = Icons.Filled.Share,
                    contentDescription = "Share",
                    onClick = onShareClick
                )
                ImageActionButton(
                    icon = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                    tint = if (isFavorite) OutdoorOrange else OutdoorBlack,
                    onClick = onFavoriteClick
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            images.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .height(7.dp)
                        .width(if (pagerState.currentPage == index) 22.dp else 7.dp)
                        .clip(RoundedCornerShape(50))
                        .background(
                            if (pagerState.currentPage == index) Color.White else Color.White.copy(alpha = 0.48f)
                        )
                )
            }
        }
    }
}

@Composable
private fun ImageActionButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    tint: Color = OutdoorBlack
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(42.dp)
            .background(Color.White.copy(alpha = 0.92f), CircleShape)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(22.dp)
        )
    }
}

@Composable
private fun HotelHeader(hotel: HotelDetailUiModel) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        AppText.Headline(text = hotel.name, color = OutdoorBlack)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = null,
                tint = OutdoorBlue,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            AppText.Body(
                text = hotel.location,
                color = OutdoorGray,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = OutdoorGold,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            AppText.LabelAccent(text = hotel.reviewAverage.toString(), color = OutdoorBlack)
            Spacer(modifier = Modifier.width(4.dp))
            AppText.Body(text = "(${hotel.totalReviews})", color = OutdoorGray)
        }
    }
}

@Composable
private fun DetailSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        AppText.SubHeadline(text = title)
        content()
    }
}

@Composable
private fun CheckTimesRow(
    checkInTime: String,
    checkOutTime: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TimeTile(
            title = "Check In",
            time = checkInTime,
            modifier = Modifier.weight(1f)
        )
        TimeTile(
            title = "Check Out",
            time = checkOutTime,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun TimeTile(
    title: String,
    time: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFF3F7FF)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AccessTime,
                contentDescription = null,
                tint = OutdoorBlue,
                modifier = Modifier.size(22.dp)
            )
            Column {
                AppText.Body(text = title, color = OutdoorGray)
                AppText.CardTitle(text = time, color = OutdoorBlack)
            }
        }
    }
}

@Composable
private fun MapPlaceholder(location: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(OutdoorNearWhite),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(18.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Map,
                contentDescription = null,
                tint = OutdoorBlue,
                modifier = Modifier.size(34.dp)
            )
            AppText.CardTitle(text = "Google Map location", color = OutdoorBlack)
            AppText.Body(
                text = location,
                color = OutdoorGray,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
private fun BulletList(
    items: List<String>,
    icon: ImageVector
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = OutdoorBlue,
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .size(17.dp)
                )
                AppText.Body(
                    text = item,
                    color = OutdoorGray,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ReviewSummary(hotel: HotelDetailUiModel) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = OutdoorNearWhite
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(OutdoorGold.copy(alpha = 0.18f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = OutdoorGold,
                    modifier = Modifier.size(26.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                AppText.CardTitle(
                    text = "${hotel.reviewAverage} Excellent",
                    color = OutdoorBlack
                )
                AppText.Body(
                    text = "Based on ${hotel.totalReviews} guest reviews",
                    color = OutdoorGray,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            AppText.LabelAccent(
                text = "Reviews",
                color = OutdoorBlue,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HotelDetailedScreenPreview() {
    OutdoorTheme {
        HotelDetailedScreen()
    }
}
