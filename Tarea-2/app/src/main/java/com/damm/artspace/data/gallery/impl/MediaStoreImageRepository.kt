package com.damm.artspace.data.gallery.impl

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.os.bundleOf
import com.damm.artspace.data.gallery.ImageRepository
import com.damm.artspace.domain.gallery.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class MediaStoreImageRepository(private val context: Context) : ImageRepository {

    private val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

    override suspend fun getImages(
        page: Int,
        pageSize: Int
    ): List<Image> = withContext(Dispatchers.IO) {
        val images = mutableListOf<Image>()
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED
        )
        val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
        val queryArgs = bundleOf(
            ContentResolver.QUERY_ARG_LIMIT to pageSize,
            ContentResolver.QUERY_ARG_OFFSET to (page - 1) * pageSize,
            ContentResolver.QUERY_ARG_SQL_SORT_ORDER to sortOrder
        )
        val query: Cursor? = getCursor(projection, queryArgs)
        query?.use { cursor ->
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
            val dateAddedColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val dateAdded = cursor.getInt(dateAddedColumn)
                val contentUri: Uri = ContentUris.withAppendedId(
                    uri,
                    id
                )
                images.add(Image(id = id, name = name, uri = contentUri, dateAddedTime = dateAdded))
            }
        }
        return@withContext images
    }

    private fun getCursor(projection: Array<String>?, queryArgs: Bundle?): Cursor? =
        context.contentResolver.query(uri, projection, queryArgs, null)

}
