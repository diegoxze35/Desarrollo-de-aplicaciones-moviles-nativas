package com.damm.artspace.repository

import com.damm.artspace.domain.Image

interface ImageRepository {
    suspend fun getImages(page: Int, pageSize: Int): List<Image>
}
