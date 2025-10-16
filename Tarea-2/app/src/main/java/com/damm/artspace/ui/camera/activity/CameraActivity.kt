package com.damm.artspace.ui.camera.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.damm.artspace.ui.camera.composable.CameraPreview
import com.damm.artspace.ui.camera.theme.ArtSpaceTheme
import com.damm.artspace.ui.camera.utils.OrientationChangeListener
import com.damm.artspace.ui.camera.viewmodel.CameraViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CameraActivity : ComponentActivity() {

    private val cameraViewModel: CameraViewModel by viewModel()
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
        enableEdgeToEdge()
        setContent {
            cameraViewModel.bind(this)
            val uiState by cameraViewModel.uiState.collectAsStateWithLifecycle()
            val orientation by animateFloatAsState(
                targetValue = orientationState,
                animationSpec = tween(durationMillis = 500),
                label = "orientationAnimation"
            )
            val context = LocalContext.current

            ArtSpaceTheme {
                CameraPreview(
                    modifier = Modifier.fillMaxSize(),
                    cameraState = uiState.cameraState,
                    orientationDegreesState = orientation,
                    isSelectedFlash = uiState.isSelectedFlash,
                    timerState = uiState.timerState,
                    onChangeIsSelectedFlashMode = cameraViewModel::toggleFlashSelection,
                    onChangeCamera = cameraViewModel::changeCamera,
                    onFlashModeChange = cameraViewModel::changeFlashMode,
                    onTakePhoto = { cameraViewModel.takePhoto(context) },
                    onTimerChange = cameraViewModel::setTimer,
                    onFilterChange = cameraViewModel::setFilter
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
