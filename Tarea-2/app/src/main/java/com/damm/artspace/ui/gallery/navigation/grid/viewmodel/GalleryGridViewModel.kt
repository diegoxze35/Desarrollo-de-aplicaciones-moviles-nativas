package com.damm.artspace.ui.gallery.navigation.grid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damm.artspace.data.gallery.ImageRepository
import com.damm.artspace.ui.gallery.navigation.grid.state.GalleryGridState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val PAGE_SIZE = 15
internal class GalleryGridViewModel(private val imageRepository: ImageRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<GalleryGridState>(GalleryGridState.Loading)
    val uiState: StateFlow<GalleryGridState> = _uiState.asStateFlow()

    private var currentPage = 1
    private var isLoadingNextPage = false

    fun loadNextPage() {
        if (isLoadingNextPage) return

        viewModelScope.launch {
            isLoadingNextPage = true
            try {
                val newImages = imageRepository.getImages(currentPage++, PAGE_SIZE)
                _uiState.update { currentState ->
                    val currentImages = if (currentState is GalleryGridState.Success)
                        currentState.images
                    else
                        emptyList()
                    GalleryGridState.Success(currentImages + newImages)
                }
            } catch (e: Exception) {
                _uiState.value = GalleryGridState.Error(e.message.orEmpty())
            } finally {
                isLoadingNextPage = false
            }
        }
    }

}
