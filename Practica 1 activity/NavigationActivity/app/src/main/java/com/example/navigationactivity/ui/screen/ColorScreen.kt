package com.example.navigationactivity.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.navigationactivity.ui.theme.NavigationActivityTheme

@Composable
fun ColorScreen(modifier: Modifier = Modifier) {
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		val radioColors = listOf(Color.Red, Color.Green, Color.Blue)
		val (selectedOption, onOptionSelected) = remember {
			mutableStateOf(radioColors[0])
		}
		val target by animateColorAsState(
			targetValue = selectedOption,
			animationSpec = tween(1000, easing = LinearEasing)
		)
		Box(
			modifier = Modifier
				.size(120.dp)
				.clip(CircleShape)
				.background(color = target)
		)
		Spacer(modifier = Modifier.size(16.dp))
		radioColors.forEach {
			Row(
				Modifier
					.fillMaxWidth()
					.selectable(
						selected = (it == selectedOption),
						onClick = { onOptionSelected(it) },
						role = Role.RadioButton
					)
					.padding(horizontal = 16.dp, vertical = 4.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				RadioButton(
					selected = (it == selectedOption),
					onClick = null // null recommended for accessibility with screen readers
				)
				Text(
					text = "$it",
					style = MaterialTheme.typography.bodyLarge,
					modifier = Modifier.padding(start = 16.dp)
				)
			}
		}
	}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CalculatorPreview() {
	NavigationActivityTheme {
		Scaffold {
			ColorScreen(
				modifier = Modifier
					.fillMaxSize()
					.padding(it)
			)
		}
	}
}