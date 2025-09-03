package com.example.myexample.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myexample.R

@Composable
fun ImageScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Card {
            var expanded by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier.clickable { expanded = !expanded },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
	                painter = painterResource(R.drawable.jetpack_compose),
	                contentDescription = stringResource(R.string.description)
				)
                AnimatedVisibility(visible = expanded) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = "Jetpack Compose",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}