package com.damm.artspace.ui.canvas.navigation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.damm.artspace.R
import com.damm.artspace.ui.canvas.state.CanvasState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawingScreen(
	onReturnToCanvasList: () -> Unit,
	onSaveBitmap: () -> Unit,
	onUndoLastPath: () -> Unit,
	onClearCanvas: () -> Unit,
	onPathStarted: (Offset) -> Unit,
	onPathUpdated: (Offset) -> Unit,
	canvasState: CanvasState,
	onColorChange: (Color) -> Unit,
	onStrokeWidthChange: (Float) -> Unit
) {
	var canvasSize by remember { mutableStateOf(IntSize.Zero) }
	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text("Lienzo de Dibujo") },
				navigationIcon = {
					IconButton(onClick = onReturnToCanvasList) {
						Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(R.string.go_back))
					}
				},
				actions = {
					IconButton(onClick = onUndoLastPath) {
						Icon(painter = painterResource(R.drawable.undo_24px),
							stringResource(R.string.undo)
						)
					}
					IconButton(onClick = onClearCanvas) {
						Icon(Icons.Filled.Delete, stringResource(R.string.delete_all))
					}
					IconButton(
						onClick = {
							if (canvasSize != IntSize.Zero) onSaveBitmap()
						}
					) {
						Icon(Icons.Filled.Done, stringResource(R.string.save))
					}
				}
			)
		}
	) { paddingValues ->
		Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
			DrawingCanvas(
				paths = canvasState.paths,
				modifier = Modifier
					.fillMaxWidth()
					.weight(1f)
					.background(Color.White)
					.onSizeChanged { canvasSize = it },
				onPathStarted = onPathStarted,
				onPathUpdated = onPathUpdated
			)
			BrushControls(
				color = canvasState.currentColor,
				onColorChange = onColorChange,
				strokeWidth = canvasState.currentStrokeWidth,
				onStrokeWidthChange = onStrokeWidthChange,
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 8.dp)
			)
		}
	}
}
