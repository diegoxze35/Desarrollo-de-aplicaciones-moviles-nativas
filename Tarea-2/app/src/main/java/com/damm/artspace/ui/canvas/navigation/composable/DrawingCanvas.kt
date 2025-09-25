package com.damm.artspace.ui.canvas.navigation.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import com.damm.artspace.domain.canvas.PathState

@Composable
internal fun DrawingCanvas(
	paths: List<PathState>,
	modifier: Modifier = Modifier,
	onPathStarted: (Offset) -> Unit,
	onPathUpdated: (Offset) -> Unit
) {
    Canvas(
        modifier = modifier.pointerInput(Unit) {
            detectDragGestures(
                onDragStart = { offset -> onPathStarted(offset) },
                onDrag = { change, _ -> onPathUpdated(change.position) }
            )
        }
    ) {
        paths.forEach { pathState ->
            drawPath(
                path = pathState.path,
                color = pathState.color,
                style = Stroke(
	                width = pathState.strokeWidth,
	                cap = StrokeCap.Round,
	                join = StrokeJoin.Round
                )
            )
        }
    }
}
