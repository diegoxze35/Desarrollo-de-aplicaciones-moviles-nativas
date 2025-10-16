package com.damm.artspace.ui.camera.state

internal sealed class TimerState(val duration: Long) {
    object Off : TimerState(0)
    object Sec3 : TimerState(3000)
    object Sec5 : TimerState(5000)
    object Sec10 : TimerState(10000)
}