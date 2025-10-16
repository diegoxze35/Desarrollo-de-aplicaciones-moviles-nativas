package com.damm.artspace.ui.camera.state

internal sealed class FilterState {
    object None : FilterState()
    object Grayscale : FilterState()
    object Sepia : FilterState()
    data class BrightnessContrast(val brightness: Float, val contrast: Float) : FilterState()
}
