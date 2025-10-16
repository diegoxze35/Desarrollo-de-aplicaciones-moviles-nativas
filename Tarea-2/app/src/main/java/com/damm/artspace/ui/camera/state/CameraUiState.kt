package com.damm.artspace.ui.camera.state

internal data class CameraUiState(
    val cameraState: CameraState,
    val isSelectedFlash: Boolean = false,
    val timerState: TimerState = TimerState.Off,
    val filterState: FilterState = FilterState.None
)