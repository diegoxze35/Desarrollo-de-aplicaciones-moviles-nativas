package com.damm.artspace.ui.camera.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.camera.core.FocusMeteringAction
import androidx.camera.core.FocusMeteringResult
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.damm.artspace.R
import com.damm.artspace.ui.camera.state.CameraState
import com.damm.artspace.ui.camera.state.FilterState
import com.damm.artspace.ui.camera.state.FlashState
import com.damm.artspace.ui.camera.state.TapFocusedState
import com.damm.artspace.ui.camera.state.TimerState
import com.damm.artspace.ui.camera.utils.TouchVisualizer
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private var focusedSuccessfully: ListenableFuture<FocusMeteringResult>? = null
private var job: Job =
    CoroutineScope(Dispatchers.Main).launch(start = CoroutineStart.LAZY, block = {})

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun CameraPreview(
    modifier: Modifier = Modifier,
    cameraState: CameraState,
    orientationDegreesState: Float,
    isSelectedFlash: Boolean,
    timerState: TimerState,
    onChangeIsSelectedFlashMode: () -> Unit,
    onChangeCamera: () -> Unit,
    onFlashModeChange: (FlashState) -> Unit,
    onTakePhoto: () -> Unit,
    onTimerChange: (TimerState) -> Unit,
    onFilterChange: (FilterState) -> Unit
) {
    var showTimerOptions by remember { mutableStateOf(false) }
    var showFilterOptions by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        val windowInfo = LocalWindowInfo.current.containerDpSize
        val dpHeight = windowInfo.height / 8
        val padding = windowInfo.width / 10
        var position by remember { mutableStateOf(IntOffset.Zero) }
        val newPosition by animateIntOffsetAsState(targetValue = position, label = "")
        var focusedIndicator by remember { mutableStateOf(TapFocusedState()) }
        val coroutine = rememberCoroutineScope()
        val sizeIndicator = 70.dp//dimensionResource(id = R.dimen.indicator_focused_size)
        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = {
                PreviewView(it).apply {
                    controller = cameraState.cameraController
                    setOnClickListener(null)
                    setOnTouchListener(
                        TouchVisualizer(
                            job = job,
                            coroutine = coroutine,
                            focusedSuccessfully = focusedSuccessfully,
                            onStartFocusAndMetering = { x, y ->
                                val point = meteringPointFactory.createPoint(x, y)
                                val action = FocusMeteringAction.Builder(point).build()
                                cameraState.cameraController.cameraControl?.startFocusAndMetering(
                                    action
                                )
                            },
                            onCancelFocus = { cameraState.cameraController.cameraControl?.cancelFocusAndMetering() },
                            onFocusedIndicatorChange = { newState ->
                                focusedIndicator = newState.copy()
                            },
                            onPositionChange = { newPosition -> position = newPosition }
                        )
                    )
                }
            },
            update = { preview ->
                preview.controller?.let {
                    it.cameraSelector = cameraState.currentCameraSelector
                    it.imageCaptureFlashMode = cameraState.flashState.mode
                }
            }
        )

        TopBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 24.dp),
            timerState = timerState,
            showTimerOptions = showTimerOptions,
            onShowTimerOptionsChange = { showTimerOptions = it },
            onTimerChange = onTimerChange,
            showFilterOptions = showFilterOptions,
            onShowFilterOptionsChange = { showFilterOptions = it },
            onFilterChange = onFilterChange,
            orientationDegreesState = orientationDegreesState
        )

        AnimatedVisibility(
            visible = focusedIndicator.isVisible,
            enter = scaleIn() + slideIn {
                IntOffset(
                    x = position.x,
                    y = position.y
                )
            },
            exit = scaleOut() + slideOut {
                IntOffset(
                    x = position.x,
                    y = position.y
                )
            }
        ) {

            Box(
                modifier = Modifier
                    .size(sizeIndicator)
                    .offset { newPosition }
                    .background(Color.Transparent)
                    .border(width = 3.dp, color = Color.White, shape = CircleShape)
            )
        }
        Row(
            modifier = Modifier
                .align(BottomCenter)
                .fillMaxWidth()
                .height(dpHeight)
                .alpha(0.65f)
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val size = dpHeight / 2
            CameraButton(
                modifier = Modifier
                    .padding(start = padding)
                    .size(size)
                    .align(CenterVertically),
                isSelectedFlash = isSelectedFlash,
                orientationDegreesState = orientationDegreesState,
                icon = cameraState.flashState.icon,
                flashMode = FlashState.FlashOFF,
                description = R.string.change_flash,
                onFlashModeChange = onFlashModeChange,
                onChangeIsSelectedFlashMode = onChangeIsSelectedFlashMode,
                onClick = onChangeIsSelectedFlashMode
            )
            CameraButton(
                modifier = Modifier
                    .size(if (!isSelectedFlash) dpHeight else size)
                    .align(CenterVertically),
                isSelectedFlash = isSelectedFlash,
                orientationDegreesState = orientationDegreesState,
                icon = R.drawable.outline_camera_24,
                flashMode = FlashState.FlashAuto,
                description = R.string.take_photo,
                onFlashModeChange = onFlashModeChange,
                onChangeIsSelectedFlashMode = onChangeIsSelectedFlashMode,
                onClick = onTakePhoto
            )
            CameraButton(
                modifier = Modifier
                    .padding(end = padding)
                    .size(size)
                    .align(CenterVertically),
                isSelectedFlash = isSelectedFlash,
                orientationDegreesState = orientationDegreesState,
                icon = R.drawable.flip_camera_android_24px,
                flashMode = FlashState.FlashON,
                description = R.string.change_camera,
                onFlashModeChange = onFlashModeChange,
                onChangeIsSelectedFlashMode = onChangeIsSelectedFlashMode,
                onClick = onChangeCamera
            )
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    timerState: TimerState,
    showTimerOptions: Boolean,
    onShowTimerOptionsChange: (Boolean) -> Unit,
    onTimerChange: (TimerState) -> Unit,
    showFilterOptions: Boolean,
    onShowFilterOptionsChange: (Boolean) -> Unit,
    onFilterChange: (FilterState) -> Unit,
    orientationDegreesState: Float
) {
    Column(modifier = modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.65f)
                .background(MaterialTheme.colorScheme.secondaryContainer),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = CenterVertically
        ) {
            IconButton(onClick = {
                onShowTimerOptionsChange(!showTimerOptions)
                onShowFilterOptionsChange(false)
            }) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .rotate(orientationDegreesState),
                    painter = painterResource(id = timerState.icon),
                    contentDescription = "Timer",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = {
                onShowFilterOptionsChange(!showFilterOptions)
                onShowTimerOptionsChange(false)
            }) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .rotate(orientationDegreesState),
                    painter = painterResource(id = R.drawable.filter_vintage_24px),
                    contentDescription = "Filters",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }

        AnimatedVisibility(visible = showTimerOptions) {
            TimerOptions(onTimerChange = { onTimerChange(it); onShowTimerOptionsChange(false) })
        }

        AnimatedVisibility(visible = showFilterOptions) {
            FilterOptions(onFilterChange = { onFilterChange(it); onShowFilterOptionsChange(false) })
        }
    }
}

@Composable
private fun TimerOptions(onTimerChange: (TimerState) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.5f), CircleShape)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Off", Modifier.clickable { onTimerChange(TimerState.Off) }, color = Color.White)
        Text("3s", Modifier.clickable { onTimerChange(TimerState.Sec3) }, color = Color.White)
        Text("5s", Modifier.clickable { onTimerChange(TimerState.Sec5) }, color = Color.White)
        Text("10s", Modifier.clickable { onTimerChange(TimerState.Sec10) }, color = Color.White)
    }
}

@Composable
private fun FilterOptions(onFilterChange: (FilterState) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.5f), CircleShape)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("None", Modifier.clickable { onFilterChange(FilterState.None) }, color = Color.White)
        Text(
            "Gray",
            Modifier.clickable { onFilterChange(FilterState.Grayscale) },
            color = Color.White
        )
        Text("Sepia", Modifier.clickable { onFilterChange(FilterState.Sepia) }, color = Color.White)
    }
}

@Composable
private fun CameraButton(
    modifier: Modifier = Modifier,
    isSelectedFlash: Boolean,
    orientationDegreesState: Float,
    @DrawableRes icon: Int,
    flashMode: FlashState,
    @StringRes description: Int,
    onFlashModeChange: (FlashState) -> Unit,
    onChangeIsSelectedFlashMode: () -> Unit,
    onClick: () -> Unit,
) = IconButton(
    onClick = {
        if (!isSelectedFlash)
            onClick()
        else {
            onFlashModeChange(flashMode)
            onChangeIsSelectedFlashMode()
        }
    },
    modifier = modifier
) {
    Icon(
        painter = painterResource(
            id = if (!isSelectedFlash)
                icon
            else
                flashMode.icon
        ),
        contentDescription = stringResource(id = description),
        modifier = Modifier
            .fillMaxSize()
            .rotate(orientationDegreesState),
        tint = MaterialTheme.colorScheme.onSecondaryContainer
    )
}
