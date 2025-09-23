package com.damm.artspace.data.gallery

import com.damm.artspace.domain.gallery.Image
import kotlinx.coroutines.flow.StateFlow

interface CachedImageRepository {

    fun setCachedImages(items: List<Image>)
    val cachedImages: StateFlow<List<Image>>

}
