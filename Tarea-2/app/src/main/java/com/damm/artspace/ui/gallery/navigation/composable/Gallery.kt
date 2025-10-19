package com.damm.artspace.ui.gallery.navigation.composable

import android.Manifest
import android.os.Build
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.damm.artspace.ui.common.state.TopAppBarState
import com.damm.artspace.ui.gallery.navigation.ImageScreen
import com.damm.artspace.ui.gallery.navigation.permissions.RequiredPermission
import com.damm.artspace.ui.gallery.navigation.state.GalleryGridState
import com.damm.artspace.ui.gallery.navigation.viewmodel.GalleryGridViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun Gallery(
	modifier: Modifier,
	onConfigureTopAppBar: (TopAppBarState) -> Unit,
	navController: NavController,
	lazyGridState: LazyGridState,
	showCameraPermissionDialog: Boolean,
	shouldShowCameraRationale: Boolean,
	onRequestCameraPermission: () -> Unit,
	onDismissCameraPermissionDialog: () -> Unit
) {
    if (showCameraPermissionDialog) {
        Dialog(onDismissRequest = onDismissCameraPermissionDialog) {
            Surface(
                modifier = Modifier.clip(RoundedCornerShape(48.dp))
            ) {
                PermissionRequestScreen(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(all = 32.dp),
                    shouldShowRationale = shouldShowCameraRationale,
                    onLaunchPermissionRequest = onRequestCameraPermission,
                    requiredPermission = RequiredPermission.CameraPermission
                )
            }
        }
    }
    val mediaPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        Manifest.permission.READ_MEDIA_IMAGES
    else
        Manifest.permission.READ_EXTERNAL_STORAGE
    val activity = LocalActivity.current!!
    var shouldShowMediaRationale by rememberSaveable {
        mutableStateOf(true)
    }
    val readImagesPermission = rememberPermissionState(mediaPermission) { isGranted ->
        if (!isGranted)
            shouldShowMediaRationale =
                shouldShowRequestPermissionRationale(activity, mediaPermission)
    }
    if (readImagesPermission.status.isGranted) {
        val galleryGridViewModel: GalleryGridViewModel = koinViewModel()
        val uiState by galleryGridViewModel.uiState.collectAsStateWithLifecycle()
        when (val state = uiState) {
            GalleryGridState.Loading -> {
                Box(modifier = modifier, contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
                galleryGridViewModel.loadNextPage()
            }

            is GalleryGridState.Error -> Text(modifier = modifier, text = state.message)
            is GalleryGridState.Success -> GalleryGridScreen(
                modifier = modifier,
                images = state.images,
                lazyGridState = lazyGridState,
                onLoadNextPage = galleryGridViewModel::loadNextPage,
                onConfigureTopAppBar = onConfigureTopAppBar,
                onImageClick = { index ->
                    galleryGridViewModel.setCachedImages(state.images)
                    navController.navigate(ImageScreen(index))
                }
            )
        }
    } else {
        PermissionRequestScreen(
            modifier = modifier,
            shouldShowRationale = shouldShowMediaRationale,
            onLaunchPermissionRequest = readImagesPermission::launchPermissionRequest,
            requiredPermission = RequiredPermission.ReadImagesPermission
        )
    }
}
