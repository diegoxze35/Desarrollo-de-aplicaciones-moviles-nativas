package com.damm.artspace.ui.camera.state

import androidx.annotation.DrawableRes
import com.damm.artspace.R

internal sealed class TimerState(val duration: Int, @DrawableRes val icon: Int) {
    object Off : TimerState(0, R.drawable.timer_off_24px)
    object Sec3 : TimerState(3, R.drawable.timer_3_alt_1_24px)
    object Sec5 : TimerState(5, R.drawable.timer_5_24px)
    object Sec10 : TimerState(10, R.drawable.timer_10_alt_1_24px)
}
