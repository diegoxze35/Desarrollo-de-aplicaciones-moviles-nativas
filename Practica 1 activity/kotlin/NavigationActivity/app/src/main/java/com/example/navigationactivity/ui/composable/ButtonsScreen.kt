package com.example.navigationactivity.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.navigationactivity.R


@Composable
fun ButtonsScreen() {
    var clickCount by remember { mutableIntStateOf(0) }
    var selectedButton: String? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.buttons_description),
            style = MaterialTheme.typography.bodyMedium
        )

        // Filled Button
        Button(
            onClick = {
                clickCount++
                selectedButton = "Filled Button"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Star, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(R.string.button_filled))
        }

        // Outlined Button
        OutlinedButton(
            onClick = {
                clickCount++
                selectedButton = "Outlined Button"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Favorite, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(R.string.button_outlined))
        }

        // Text Button
        TextButton(
            onClick = {
                clickCount++
                selectedButton = "Text Button"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Share, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(R.string.button_text))
        }

        // Icon Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    clickCount++
                    selectedButton = "Icon Button 1"
                }
            ) {
                Icon(Icons.Default.Home, contentDescription = stringResource(R.string.cd_home))
            }

            IconButton(
                onClick = {
                    clickCount++
                    selectedButton = "Icon Button 2"
                }
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = stringResource(R.string.cd_settings)
                )
            }

            IconButton(
                onClick = {
                    clickCount++
                    selectedButton = "Icon Button 3"
                }
            ) {
                Icon(Icons.Default.Search, contentDescription = stringResource(R.string.cd_search))
            }
        }

        // Floating Action Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            FloatingActionButton(
                onClick = {
                    clickCount++
                    selectedButton = "FAB"
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.cd_add))
            }
        }

        // Status display
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(stringResource(R.string.buttons_current_state), fontWeight = FontWeight.Bold)
                Text(stringResource(R.string.buttons_total_clicks, clickCount))
                Text(stringResource(R.string.buttons_last_pressed, selectedButton.orEmpty()))
            }
        }
    }
}