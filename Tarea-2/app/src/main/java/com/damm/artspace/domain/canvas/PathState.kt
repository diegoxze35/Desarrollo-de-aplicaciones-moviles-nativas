package com.damm.artspace.domain.canvas

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
data class PathState(
	val path: Path,
	val color: Color,
	val strokeWidth: Float
)
