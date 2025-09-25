package com.damm.artspace.data.canvas

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap

interface CanvasRepository {
	suspend fun saveBitmap(bitmap: Bitmap): String
	suspend fun getBitmaps(): List<ImageBitmap>
}