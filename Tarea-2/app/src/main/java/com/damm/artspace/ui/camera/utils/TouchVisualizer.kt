package com.damm.artspace.ui.camera.utils

import android.view.MotionEvent
import android.view.View
import androidx.camera.core.FocusMeteringResult
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.damm.artspace.ui.camera.state.TapFocusedState
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

internal class TouchVisualizer(
    private var job: Job,
    private val coroutine: CoroutineScope,
    private var focusedSuccessfully: ListenableFuture<FocusMeteringResult>?,
    private val onStartFocusAndMetering: (x: Float, y: Float) -> ListenableFuture<FocusMeteringResult>?,
    private val onCancelFocus: () -> Unit,
    private val onFocusedIndicatorChange: (newState: TapFocusedState) -> Unit,
    private val onPositionChange: (IntOffset) -> Unit,
    private val sizeIndicator: Dp = 70.dp
) : View.OnTouchListener {
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_UP -> {
                if (job.isActive) {
                    focusedSuccessfully?.cancel(true)
                    onCancelFocus()
                    //cameraState.cameraController.cameraControl?.cancelFocusAndMetering()
                    job.cancel()
                }
                onFocusedIndicatorChange(TapFocusedState(isVisible = true))
                /*focusedIndicator = focusedIndicator.copy(isVisible = true)*/
                /*val point = onCreatePoint(event.x, event.y)
                val action = FocusMeteringAction.Builder(point).build()*/
                focusedSuccessfully = onStartFocusAndMetering(event.x, event.y)
                    /*cameraState.cameraController.cameraControl?.startFocusAndMetering(
                        action
                    )*/
                onPositionChange(
                    (sizeIndicator / 2).run {
                        IntOffset(
                            event.x.roundToInt() - value.roundToInt(),
                            event.y.roundToInt() - value.roundToInt()
                        )
                    }
                )
                /*position = (sizeIndicator / 2).run {
                    IntOffset(
                        event.x.roundToInt() - value.roundToInt(),
                        event.y.roundToInt() - value.roundToInt()
                    )
                }*/
                job = coroutine.launch {
                    val success = withContext(Dispatchers.IO) {
                        focusedSuccessfully?.get()?.isFocusSuccessful == true
                    }
                    onFocusedIndicatorChange(
                        TapFocusedState(isFocusSuccess = success)
                    )
                    /*focusedIndicator =
                        focusedIndicator.copy(isFocusSuccess = success)*/
                    delay(500)
                    onFocusedIndicatorChange(
                        TapFocusedState(
                            isFocusSuccess = false,
                            isVisible = false
                        )
                    )
                    /*focusedIndicator = focusedIndicator.copy(
                        isFocusSuccess = false,
                        isVisible = false
                    )*/
                }
                false
            }
            MotionEvent.ACTION_BUTTON_PRESS -> v.performClick()
            else -> false
        }
    }
}