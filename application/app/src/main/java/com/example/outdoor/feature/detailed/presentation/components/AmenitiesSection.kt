package com.example.outdoor.feature.detailed.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppText
import com.example.outdoor.ui.theme.OutdoorBlack
import com.example.outdoor.ui.theme.OutdoorBlue
import com.example.outdoor.ui.theme.OutdoorNearWhite

private const val CollapsedAmenityCount = 4

@Composable
fun AmenitiesSection(
    amenities: List<String>,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val visibleAmenities = if (isExpanded) amenities else amenities.take(CollapsedAmenityCount)
    val hiddenCount = (amenities.size - CollapsedAmenityCount).coerceAtLeast(0)

    if (isExpanded) {
        FlowRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            visibleAmenities.forEach { amenity ->
                AmenityTile(
                    icon = Icons.Filled.CheckCircle,
                    text = amenity
                )
            }

            if (hiddenCount > 0) {
                AmenityTile(
                    icon = Icons.Filled.KeyboardArrowUp,
                    text = "Show less",
                    iconTint = OutdoorBlue,
                    textColor = OutdoorBlue,
                    modifier = Modifier.clickable { isExpanded = false }
                )
            }
        }
    } else {
        LazyRow(
            modifier = modifier.heightIn(min = 42.dp),
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(visibleAmenities) { amenity ->
                AmenityTile(
                    icon = Icons.Filled.CheckCircle,
                    text = amenity
                )
            }

            if (hiddenCount > 0) {
                item {
                    AmenityTile(
                        icon = Icons.Filled.CheckCircle,
                        text = "+$hiddenCount more",
                        iconTint = OutdoorBlue,
                        textColor = OutdoorBlue,
                        modifier = Modifier.clickable { isExpanded = true }
                    )
                }
            }
        }
    }
}

@Composable
fun AmenityTile(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier,
    iconTint: Color = OutdoorBlue,
    textColor: Color = OutdoorBlack
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        color = OutdoorNearWhite
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(17.dp)
            )
            AppText.Label(
                text = text,
                color = textColor
            )
        }
    }
}
