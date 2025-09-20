package com.damm.artspace.repository.impl

import android.content.Context
import com.damm.artspace.domain.Image
import com.damm.artspace.repository.ImageRepository

class ImageRepositoryImpl(private val context: Context) : ImageRepository {
	override suspend fun getImages(
		page: Int,
		pageSize: Int
	): List<Image> {
		return emptyList()
	}
}