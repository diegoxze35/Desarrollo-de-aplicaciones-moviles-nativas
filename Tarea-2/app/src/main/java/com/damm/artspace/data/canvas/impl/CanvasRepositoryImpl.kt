package com.damm.artspace.data.canvas.impl

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.damm.artspace.data.canvas.CanvasRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

private const val DRAWINGS_DIR = "drawings"

internal class CanvasRepositoryImpl(private val context: Context) : CanvasRepository {
	
	override suspend fun saveBitmap(bitmap: Bitmap): String {
		return withContext(Dispatchers.IO) {
			val directory = File(context.filesDir, DRAWINGS_DIR)
			if (!directory.exists()) {
				directory.mkdirs()
			}
			val file = File(directory, "drawing_${System.currentTimeMillis()}.png")
			FileOutputStream(file).use { out ->
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
			}
			file.absolutePath
		}
	}
	
	override suspend fun getBitmaps(): List<ImageBitmap> {
		return withContext(Dispatchers.IO) {
			val directory = File(context.filesDir, DRAWINGS_DIR)
			if (!directory.exists() || !directory.isDirectory) {
				return@withContext emptyList()
			}
			directory.listFiles()
				?.filter { it.isFile && it.extension.equals("png", ignoreCase = true) }
				?.sortedByDescending { it.lastModified() }
				?.mapNotNull { BitmapFactory.decodeFile(it.absolutePath)?.asImageBitmap() }
				?: emptyList()
		}
	}
	
}
