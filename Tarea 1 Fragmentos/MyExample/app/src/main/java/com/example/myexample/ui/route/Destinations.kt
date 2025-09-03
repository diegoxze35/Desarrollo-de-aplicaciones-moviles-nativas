package com.example.myexample.ui.route

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.SmartButton
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myexample.R
import kotlinx.serialization.Serializable

enum class Destinations(
	val icon: ImageVector,
	@StringRes val labelId: Int,
	val route: Any
) {
	CALCULATOR(Icons.Filled.Calculate, R.string.calculator, Calculator),
	RADIO_BUTTONS(Icons.Filled.ColorLens, R.string.color, Color),
	IMAGE(Icons.Filled.Image, R.string.image, Image),
	LIST(Icons.Filled.FormatListNumbered, R.string.list, List),
	BUTTONS(Icons.Filled.SmartButton, R.string.button, Button)
}

@Serializable
data object Calculator

@Serializable
data object Color

@Serializable
data object Image

@Serializable
data object List

@Serializable
data object Button