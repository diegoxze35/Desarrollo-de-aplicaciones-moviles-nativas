package com.damm.artspace.di

import android.hardware.camera2.CameraMetadata.FLASH_MODE_OFF
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.view.LifecycleCameraController
import com.damm.artspace.data.canvas.CanvasRepository
import com.damm.artspace.data.canvas.impl.CanvasRepositoryImpl
import com.damm.artspace.data.gallery.CachedImageRepository
import com.damm.artspace.data.gallery.ImageRepository
import com.damm.artspace.data.gallery.impl.CachedImageRepositoryImpl
import com.damm.artspace.data.gallery.impl.MediaStoreImageRepository
import com.damm.artspace.ui.camera.viewmodel.CameraViewModel
import com.damm.artspace.ui.canvas.navigation.viewmodel.CanvasListViewModel
import com.damm.artspace.ui.canvas.navigation.viewmodel.DrawingViewModel
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
			isTapToFocusEnabled = false /*Tap to focused manually*/
			imageCaptureMode = ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
			cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
			imageCaptureFlashMode = FLASH_MODE_OFF
		}
	}

	single<CanvasRepository> {
		CanvasRepositoryImpl(context = get())
	}
	
	viewModelOf(constructor = ::GalleryGridViewModel)
	viewModelOf(constructor = ::ImagePagerViewModel)
	viewModelOf(constructor = ::CanvasListViewModel)
	viewModelOf(constructor = ::DrawingViewModel)
	viewModelOf(constructor = ::CameraViewModel)

}
