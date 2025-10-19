package com.damm.artspace.ui.camera.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
                Box(modifier = Modifier.fillMaxSize()) {
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
                        onFilterChange = {
                            Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
                            cameraViewModel::setFilter
                        }
                    )
                    if (uiState.isTakingPhoto) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize()
                                .alpha(0.5f)
                                .background(color = MaterialTheme.colorScheme.tertiaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CircularProgressIndicator(
                                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                AnimatedVisibility(uiState.remainingDelay > 0) {
                                    Text(
                                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                                        text = "${uiState.remainingDelay}",
                                        style = MaterialTheme.typography.headlineLarge,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                }
                            }
                        }
                    }
                }
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

    override fun onDestroy() {
        super.onDestroy()
        cameraViewModel.unbind()
    }

}
