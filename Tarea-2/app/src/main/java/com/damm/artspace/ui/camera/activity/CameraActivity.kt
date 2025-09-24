package com.damm.artspace.ui.camera.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.damm.artspace.ui.camera.composable.CameraPreview
import com.damm.artspace.ui.camera.state.CameraState
import com.damm.artspace.ui.camera.state.FlashState
import com.damm.artspace.ui.camera.theme.ArtSpaceTheme
import com.damm.artspace.ui.camera.utils.OrientationChangeListener
import org.koin.android.ext.android.inject

class CameraActivity : ComponentActivity() {
	
	val cameraController by inject<LifecycleCameraController>()
	private var orientationState by mutableFloatStateOf(0f)
	private val orientationListener by lazy {
		OrientationChangeListener(
			context = this,
			getLastValue = { orientationState },
			onOrientationChanged = { orientationState = it }
		)
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		cameraController.bindToLifecycle(this)
		enableEdgeToEdge()
		setContent {
			var isSelectedFlash by remember { mutableStateOf(false) }
			var cameraState by remember {
				mutableStateOf(
					CameraState(
						cameraController = cameraController,
						currentCameraSelector = cameraController.cameraSelector,
						flashState = FlashState.FlashOFF
					)
				)
			}
			val orientation by animateFloatAsState(
				targetValue = orientationState, animationSpec = tween(durationMillis = 500)
			)
			ArtSpaceTheme {
				CameraPreview(
					modifier = Modifier.fillMaxSize(),
					cameraState = cameraState,
					orientationDegreesState = orientation,
					isSelectedFlash = isSelectedFlash,
					onChangeIsSelectedFlashMode = { isSelectedFlash = !isSelectedFlash },
					onChangeCamera = {
						cameraState = cameraState.copy(
							currentCameraSelector = if (cameraState.currentCameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
								CameraSelector.DEFAULT_FRONT_CAMERA
							else
								CameraSelector.DEFAULT_BACK_CAMERA
						)
					},
					onFlashModeChange = { flashMode: FlashState ->
						cameraState = cameraState.copy(flashState = flashMode)
					}
				)
			}
		}
	}
	
	override fun onStart() {
		super.onStart()
		orientationListener.enable()
	}
	
	override fun onStop() {
		super.onStop()
		orientationListener.disable()
	}
	
}
