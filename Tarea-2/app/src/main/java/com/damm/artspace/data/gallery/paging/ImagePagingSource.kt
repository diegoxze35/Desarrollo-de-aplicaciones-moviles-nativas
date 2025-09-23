/*
package com.damm.artspace.data.gallery.paging

import android.content.ContentResolver
import android.database.ContentObserver
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.damm.artspace.data.gallery.ImageRepository
import com.damm.artspace.domain.gallery.Image

private const val PAGE_SIZE = 100

class ImagePagingSource(
    private val contentResolver: ContentResolver,
    private val imageRepository: ImageRepository
) : PagingSource<Int, Image>() {

    private val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    override val jumpingSupported: Boolean = true

    private val contentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            invalidate()
        }
    }

    init {
        contentResolver.registerContentObserver(
            uri,
            true,
            contentObserver
        )
        registerInvalidatedCallback {
            contentResolver.unregisterContentObserver(contentObserver)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        var limit = params.loadSize
        var offset = params.key ?: 0
        if (params is LoadParams.Refresh) {
            if (params.placeholdersEnabled) {
                limit = maxOf(limit / PAGE_SIZE, 2) * PAGE_SIZE
                val idealStart = offset - limit / 2
                offset = maxOf(0, idealStart / PAGE_SIZE * PAGE_SIZE)
            } else {
                offset = maxOf(0, offset - limit / 2)
            }
        } else {
            if (params is LoadParams.Prepend) {
                limit = minOf(limit, offset)
                offset -= limit
            }
        }
        val mediaStoreImages = imageRepository.getImages(pageSize = limit, page = offset)
        val totalCount = imageRepository.getTotalCount()
        return if (invalid) {
            LoadResult.Invalid()
        } else {
            val prevKey = if (offset == 0) null else offset
            val nextKey = offset + mediaStoreImages.size
            if (params is LoadParams.Refresh) {
                LoadResult.Page(
                    data = mediaStoreImages,
                    prevKey = if (mediaStoreImages.isEmpty()) null else prevKey,
                    nextKey = if (mediaStoreImages.isEmpty()) null else nextKey,
                    itemsBefore = offset,
                    itemsAfter = totalCount - mediaStoreImages.size - offset
                )
            } else {
                LoadResult.Page(
                    data = mediaStoreImages,
                    prevKey = if (mediaStoreImages.isEmpty() && params is LoadParams.Prepend) null else prevKey,
                    nextKey = if (mediaStoreImages.isEmpty() && params is LoadParams.Append) null else nextKey,
                    itemsBefore = offset,
                    itemsAfter = totalCount - mediaStoreImages.size - offset
                )
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        val page = state.anchorPosition?.let {
            state.closestPageToPosition(it)
        }
        val prevKey = page?.prevKey ?: 0
        val item = state.anchorPosition?.let { state.closestItemToPosition(it) }
        return prevKey + (page?.data?.indexOf(item)?.takeIf { it >= 0 } ?: 0)
    }

}
*/
