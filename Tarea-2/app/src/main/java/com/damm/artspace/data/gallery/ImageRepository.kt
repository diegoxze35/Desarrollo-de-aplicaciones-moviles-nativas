package com.damm.artspace.data.gallery

import com.damm.artspace.domain.gallery.Image

interface ImageRepository {

    suspend fun getImages(page: Int, pageSize: Int): List<Image>

}
