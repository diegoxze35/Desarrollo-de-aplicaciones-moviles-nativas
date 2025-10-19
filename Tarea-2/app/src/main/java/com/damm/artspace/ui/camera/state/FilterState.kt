package com.damm.artspace.ui.camera.state

internal sealed class FilterState(val name: String) {
    object None : FilterState("None")
    object Grayscale : FilterState("Grayscale")
    object Sepia : FilterState("Sepia")
}
