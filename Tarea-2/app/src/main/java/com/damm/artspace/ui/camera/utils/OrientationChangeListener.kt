package com.damm.artspace.ui.camera.utils

import android.content.Context
import android.view.OrientationEventListener

class OrientationChangeListener(
	context: Context,
	private val getLastValue: () -> Float,
	private val onOrientationChanged: (Float) -> Unit
) : OrientationEventListener(context) {
	override fun onOrientationChanged(orientation: Int) {
		if (orientation == ORIENTATION_UNKNOWN) return
		val degrees = when (orientation) {
			in 45 until 135 -> -90
			in 135 until 225 -> if (getLastValue() > 0f) 180 else -180
			in 225 until 315 -> 90
			else -> 0
		}
		onOrientationChanged(degrees.toFloat())
	}
}
