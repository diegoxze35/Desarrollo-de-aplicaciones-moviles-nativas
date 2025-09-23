package com.damm.artspace.data.gallery.impl

import com.damm.artspace.data.gallery.CachedImageRepository
import com.damm.artspace.domain.gallery.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CachedImageRepositoryImpl : CachedImageRepository {

    private val _cachedImages = MutableStateFlow<List<Image>>(emptyList())

    override fun setCachedImages(items: List<Image>) {
        _cachedImages.value = items
    }

    override val cachedImages: StateFlow<List<Image>>
        get() = _cachedImages

}
