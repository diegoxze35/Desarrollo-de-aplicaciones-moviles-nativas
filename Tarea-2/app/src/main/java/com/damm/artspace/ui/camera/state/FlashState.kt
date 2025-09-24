package com.damm.artspace.ui.camera.state

import androidx.annotation.DrawableRes
import androidx.camera.core.ImageCapture.FLASH_MODE_AUTO
import androidx.camera.core.ImageCapture.FLASH_MODE_OFF
import androidx.camera.core.ImageCapture.FLASH_MODE_ON
import com.damm.artspace.R

internal sealed class FlashState(val mode: Int, @DrawableRes val icon: Int) {
	object FlashOFF : FlashState(FLASH_MODE_OFF, R.drawable.outline_flash_off_24)
	object FlashAuto : FlashState(FLASH_MODE_AUTO, R.drawable.outline_flash_auto_24)
	object FlashON : FlashState(FLASH_MODE_ON, R.drawable.outline_flash_on_24)
}
