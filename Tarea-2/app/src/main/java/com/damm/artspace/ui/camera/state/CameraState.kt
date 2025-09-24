package com.damm.artspace.ui.camera.state

import androidx.camera.core.CameraSelector
import androidx.camera.view.CameraController

internal data class CameraState(
	val cameraController: CameraController,
	val currentCameraSelector: CameraSelector,
	val flashState: FlashState
)
