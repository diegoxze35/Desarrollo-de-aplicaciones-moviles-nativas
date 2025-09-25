package com.damm.artspace.ui.canvas.navigation.viewmodel

import android.graphics.Bitmap
import android.os.Build
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.IntSize
import androidx.core.graphics.createBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damm.artspace.data.canvas.CanvasRepository
import com.damm.artspace.domain.canvas.PathState
import kotlinx.coroutines.launch

class DrawingViewModel(private val canvasRepository: CanvasRepository) : ViewModel() {
	
	// Lista observable de trazos que componen el dibujo actual.
	val paths = mutableStateListOf<PathState>()
	
	// Estados para el color y grosor del pincel.
	var currentColor by mutableStateOf(Color.Black)
	var currentStrokeWidth by mutableFloatStateOf(10f)
	
	fun startPath(offset: Offset) {
		paths.add(
			PathState(
				path = Path().apply { moveTo(offset.x, offset.y) },
				color = currentColor,
				strokeWidth = currentStrokeWidth
			)
		)
	}
	
	fun updatePath(offset: Offset) {
		val lastPathState = paths.lastOrNull() ?: return
		
		// Crear una nueva instancia de Path copiando el anterior y añadiendo el nuevo punto.
		val newPath = Path()
		newPath.addPath(lastPathState.path) // Copia la geometría del trazo anterior.
		newPath.lineTo(offset.x, offset.y) // Añade el nuevo punto.
		
		// Reemplazar el último estado del trazo con uno nuevo.
		// Esto garantiza que Compose detecte el cambio y recomponga la UI.
		paths[paths.size - 1] = lastPathState.copy(path = newPath)
	}
	
	fun undoLastPath() {
		if (paths.isNotEmpty()) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
				paths.removeLast()
			} else {
				paths.removeAt(paths.lastIndex)
			}
		}
	}
	
	fun clearCanvas() {
		paths.clear()
	}
	
	private fun createBitmapFromPaths(size: IntSize): Bitmap {
		val bitmap = createBitmap(size.width, size.height)
		val canvas = android.graphics.Canvas(bitmap)
		// Dibuja un fondo blanco
		canvas.drawColor(android.graphics.Color.WHITE)
		// Dibuja cada trazo en el canvas del bitmap
		paths.forEach { pathState ->
			val paint = android.graphics.Paint().apply {
				color = pathState.color.toArgb()
				style = android.graphics.Paint.Style.STROKE
				strokeWidth = pathState.strokeWidth
				strokeCap = android.graphics.Paint.Cap.ROUND
				strokeJoin = android.graphics.Paint.Join.ROUND
				isAntiAlias = true
			}
			canvas.drawPath(pathState.path.asAndroidPath(), paint)
		}
		return bitmap
	}
	
	fun saveDrawing(canvasSize: IntSize) {
		viewModelScope.launch {
			val bitmap = createBitmapFromPaths(canvasSize)
			canvasRepository.saveBitmap(bitmap)
		}
	}
}
