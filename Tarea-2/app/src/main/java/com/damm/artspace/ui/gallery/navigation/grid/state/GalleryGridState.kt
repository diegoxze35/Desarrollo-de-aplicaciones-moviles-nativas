package com.damm.artspace.ui.gallery.navigation.grid.state

import com.damm.artspace.domain.gallery.Image

internal sealed class GalleryGridState {
    data object Loading : GalleryGridState()
    data class Success(val images: List<Image>) : GalleryGridState()
    data class Error(val message: String) : GalleryGridState()
}
