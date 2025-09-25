package com.damm.artspace.ui.canvas.navigation.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damm.artspace.data.canvas.CanvasRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class CanvasListViewModel(private val canvasRepository: CanvasRepository) : ViewModel() {
	
	private val _drawings = MutableStateFlow<List<ImageBitmap>>(emptyList())
	val drawings: StateFlow<List<ImageBitmap>> = _drawings.asStateFlow()
	
	fun loadDrawings() {
		viewModelScope.launch {
			_drawings.value = canvasRepository.getBitmaps()
		}
	}
	
}
