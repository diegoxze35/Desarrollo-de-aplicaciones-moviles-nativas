package com.damm.artspace.ui.canvas.navigation.viewmodel

import android.graphics.Bitmap
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
import com.damm.artspace.ui.canvas.state.CanvasState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class DrawingViewModel(private val canvasRepository: CanvasRepository) : ViewModel() {
    private val _canvasState = MutableStateFlow(CanvasState())
    val canvasState = _canvasState.asStateFlow()

    fun setCurrentColor(color: Color) = _canvasState.update { it.copy(currentColor = color) }

    fun setCurrentStrokeWidth(width: Float) = _canvasState.update {
        it.copy(currentStrokeWidth = width)
    }

    fun startPath(offset: Offset) {
        _canvasState.update {
            it.copy(
                paths = it.paths + PathState(
                    path = Path().apply { moveTo(offset.x, offset.y) },
                    color = it.currentColor,
                    strokeWidth = it.currentStrokeWidth
                )
            )
        }
    }

    fun updatePath(offset: Offset) {
        val lastPathState = canvasState.value.paths.lastOrNull() ?: return
        val newPath = Path()
        newPath.addPath(lastPathState.path) // Copia la geometría del trazo anterior.
        newPath.lineTo(offset.x, offset.y) // Añade el nuevo punto.
        _canvasState.update {
            it.copy(paths = it.paths.dropLast(1) + lastPathState.copy(path = newPath))
        }
    }

    fun undoLastPath() {
        if (canvasState.value.paths.isNotEmpty()) {
            _canvasState.update { it.copy(paths = it.paths.dropLast(1)) }
        }
    }

    fun clearCanvas() {
        _canvasState.update { it.copy(paths = emptyList()) }
        /*paths.clear()*/
    }

    private fun createBitmapFromPaths(size: IntSize): Bitmap {
        val bitmap = createBitmap(size.width, size.height)
        val canvas = android.graphics.Canvas(bitmap)
        canvas.drawColor(android.graphics.Color.WHITE)
        canvasState.value.paths.forEach { pathState ->
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
