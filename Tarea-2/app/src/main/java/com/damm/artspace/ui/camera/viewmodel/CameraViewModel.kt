package com.damm.artspace.ui.camera.viewmodel

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import androidx.core.graphics.createBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damm.artspace.ui.camera.activity.CameraActivity
import com.damm.artspace.ui.camera.state.CameraState
import com.damm.artspace.ui.camera.state.CameraUiState
import com.damm.artspace.ui.camera.state.FilterState
import com.damm.artspace.ui.camera.state.FlashState
import com.damm.artspace.ui.camera.state.TimerState
import com.damm.artspace.ui.common.repository.ImageChangeNotifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
private const val SECOND = 1_000L

internal class CameraViewModel(
    private val cameraController: LifecycleCameraController,
    private val notifier: ImageChangeNotifier
) : ViewModel() {

    fun bind(activity: CameraActivity) {
        cameraController.bindToLifecycle(activity)
    }

    fun unbind() {
        cameraController.unbind()
    }

    override fun onCleared() {
        super.onCleared()
        unbind()
    }

    private val _uiState = MutableStateFlow(
        CameraUiState(
            cameraState = CameraState(
                cameraController = cameraController,
                currentCameraSelector = cameraController.cameraSelector,
                flashState = FlashState.FlashOFF
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    fun changeCamera() {
        _uiState.update { currentState ->
            val newSelector =
                if (currentState.cameraState.currentCameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
                    CameraSelector.DEFAULT_FRONT_CAMERA
                else
                    CameraSelector.DEFAULT_BACK_CAMERA
            currentState.copy(cameraState = currentState.cameraState.copy(currentCameraSelector = newSelector))
        }
    }

    fun changeFlashMode(flashMode: FlashState) {
        _uiState.update {
            it.copy(cameraState = it.cameraState.copy(flashState = flashMode))
        }
    }

    fun toggleFlashSelection() {
        _uiState.update {
            it.copy(isSelectedFlash = !it.isSelectedFlash)
        }
    }

    fun setTimer(timer: TimerState) {
        _uiState.update {
            it.copy(timerState = timer)
        }
    }

    fun setFilter(filter: FilterState) {
        _uiState.update {
            it.copy(filterState = filter)
        }
    }

    fun takePhoto(context: Context) {
        _uiState.update { it.copy(isTakingPhoto = true) }
        viewModelScope.launch {
            val timerState = uiState.value.timerState
            repeat(timerState.duration + 1) { n ->
                _uiState.update { it.copy(remainingDelay = timerState.duration - n) }
                delay(SECOND)
            }
            val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
                .format(System.currentTimeMillis())
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, name)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                    put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/ArtSpace-Image")
                }
            }

            val outputOptions = ImageCapture.OutputFileOptions
                .Builder(
                    context.contentResolver,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues
                )
                .build()

            cameraController.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(context),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(exc: ImageCaptureException) {
                        _uiState.update {
                            it.copy(errorMessage = "${exc.message}", isTakingPhoto = false)
                        }
                    }

                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        val savedUri = output.savedUri ?: return
                        val msg = "Photo capture succeeded: $savedUri"
                        Log.d("CameraViewModel", msg)

                        if (uiState.value.filterState != FilterState.None) {
                            val bitmap = context.contentResolver.openInputStream(savedUri).use {
                                BitmapFactory.decodeStream(it)
                            }
                            val filteredBitmap = applyFilter(bitmap, uiState.value.filterState)
                            context.contentResolver.openOutputStream(savedUri)?.use {
                                filteredBitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                            }
                        }
                        viewModelScope.launch { notifier.notifyDataChanged() }
                        _uiState.update { it.copy(isTakingPhoto = false) }
                    }
                }
            )
        }
    }

    private fun applyFilter(bitmap: Bitmap, filter: FilterState): Bitmap {
        val newBitmap = createBitmap(bitmap.width, bitmap.height, bitmap.config!!)
        val canvas = Canvas(newBitmap)
        val paint = Paint()
        val colorMatrix = ColorMatrix()

        when (filter) {
            FilterState.Grayscale -> colorMatrix.setSaturation(0f)
            FilterState.Sepia -> {
                colorMatrix.setSaturation(0f)
                val sepiaMatrix = ColorMatrix().apply {
                    setScale(1f, 0.95f, 0.82f, 1f)
                }
                colorMatrix.postConcat(sepiaMatrix)
            }

            FilterState.None -> return bitmap
        }

        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        return newBitmap
    }
}
