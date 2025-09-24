package com.damm.artspace.di

import android.hardware.camera2.CameraMetadata.FLASH_MODE_OFF
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.view.LifecycleCameraController
import com.damm.artspace.data.gallery.CachedImageRepository
import com.damm.artspace.data.gallery.ImageRepository
import com.damm.artspace.data.gallery.impl.CachedImageRepositoryImpl
import com.damm.artspace.data.gallery.impl.MediaStoreImageRepository
import com.damm.artspace.ui.gallery.navigation.viewmodel.GalleryGridViewModel
import com.damm.artspace.ui.gallery.navigation.viewmodel.ImagePagerViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
	
	single<ImageRepository> {
		MediaStoreImageRepository(context = get())
	}
	
	single<CachedImageRepository> {
		CachedImageRepositoryImpl()
	}
	
	single<LifecycleCameraController> {
		LifecycleCameraController(get()).apply {
			unbind()
			isTapToFocusEnabled = true /*Tap to focused manually*/
			imageCaptureMode = ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
			cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
			imageCaptureFlashMode = FLASH_MODE_OFF
		}
	}
	
	viewModelOf(constructor = ::GalleryGridViewModel)
	viewModelOf(constructor = ::ImagePagerViewModel)
	
}
