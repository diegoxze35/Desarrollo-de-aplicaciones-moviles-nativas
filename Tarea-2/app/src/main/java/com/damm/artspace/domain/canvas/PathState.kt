package com.damm.artspace.domain.canvas

import androidx.compose.ui.graphics.Color

data class PathState(
	val path: T,
	val color: Color,
	val strokeWidth: Float
)
