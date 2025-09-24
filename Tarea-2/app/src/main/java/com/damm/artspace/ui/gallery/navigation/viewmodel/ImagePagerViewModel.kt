package com.damm.artspace.ui.gallery.navigation.viewmodel

import androidx.lifecycle.ViewModel
import com.damm.artspace.data.gallery.CachedImageRepository

class ImagePagerViewModel(cachedImageRepository: CachedImageRepository) : ViewModel() {
    val images = cachedImageRepository.cachedImages
}
