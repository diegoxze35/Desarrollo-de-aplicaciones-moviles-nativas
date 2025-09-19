package com.example.navigationactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navigationactivity.ui.theme.NavigationActivityTheme

class AnotherActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			NavigationActivityTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					val transition = rememberInfiniteTransition()
					val size by transition.animateFloat(
						initialValue = 0f,
						targetValue = 200f,
						animationSpec = infiniteRepeatable(
							animation = tween(1000),
							repeatMode = RepeatMode.Reverse
						),
						label = "Heart size",
					)
					Box(modifier = Modifier
						.fillMaxSize()
						.padding(innerPadding)) {
						Icon(
							imageVector = Icons.Default.HeartBroken,
							contentDescription = null,
							modifier = Modifier.align(Alignment.Center).size(size.dp)
						)
					}
				}
			}
		}
	}
}