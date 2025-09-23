package com.damm.artspace.domain.gallery.usecase

import com.damm.artspace.data.gallery.ImageRepository
import com.damm.artspace.domain.gallery.Image
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val PAGE_SIZE = 15

class GetImagesUseCase(private val imageRepository: ImageRepository) {

    private var currentPage = 1

    operator fun invoke(): Flow<Map<Int, List<Image>>> {
        return flow {
            var items: List<Image>
            do {
                items = imageRepository.getImages(currentPage++, PAGE_SIZE)
                val groupedItems = items.groupBy { it.dateAddedTime }
                groupedItems.map {

                }
                emit(groupedItems)
            } while (items.isNotEmpty())
        }
    }

}
