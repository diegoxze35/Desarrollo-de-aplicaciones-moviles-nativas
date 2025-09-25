package com.damm.artspace.ui.canvas.state

import androidx.compose.ui.graphics.Color
import com.damm.artspace.domain.canvas.PathState

data class CanvasState(
	val paths: List<PathState> = emptyList(),
	val currentColor: Color = Color.Black,
	val currentStrokeWidth: Float = 10f
)
