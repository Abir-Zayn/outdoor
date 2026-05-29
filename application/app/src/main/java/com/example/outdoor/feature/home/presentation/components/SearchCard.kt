package com.example.outdoor.feature.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.outdoor.core.ui.components.AppButton
import com.example.outdoor.core.ui.components.AppTextField
import com.example.outdoor.ui.theme.LightPrimary

private val fieldContainerColor = Color.White.copy(alpha = 0.10f)
private val fieldPlaceholderColor = Color.White.copy(alpha = 0.70f)

@Composable
fun SearchCard(
    destination: String,
    onDestinationChange: (String) -> Unit,
    dates: String,
    onDatesChange: (String) -> Unit,
    guests: String,
    onGuestsChange: (String) -> Unit,
    onFindClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AppTextField(
            value = destination,
            onValueChange = onDestinationChange,
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AppTextField(
                value = dates,
                onValueChange = onDatesChange,
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
                onValueChange = onGuestsChange,
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

        AppButton(
            text = "Find",
            onClick = onFindClick,
            containerColor = Color.White,
            textColor = LightPrimary,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = LightPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
        )
    }
}
