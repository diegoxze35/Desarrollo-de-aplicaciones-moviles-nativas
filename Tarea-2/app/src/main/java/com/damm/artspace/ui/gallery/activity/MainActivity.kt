package com.damm.artspace.ui.gallery.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.damm.artspace.ui.camera.activity.CameraActivity
import com.damm.artspace.ui.gallery.navigation.GalleryGridScreen
import com.damm.artspace.ui.gallery.navigation.ImageScreen
import com.damm.artspace.ui.gallery.navigation.composable.CameraFloatingActionButton
import com.damm.artspace.ui.gallery.navigation.composable.Gallery
import com.damm.artspace.ui.gallery.navigation.composable.ImagePagerScreen
import com.damm.artspace.ui.gallery.navigation.viewmodel.ImagePagerViewModel
import com.damm.artspace.ui.gallery.theme.ArtSpaceTheme
import com.damm.artspace.ui.gallery.utils.isScrollUp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                val lazyGridState = rememberLazyGridState()
                val cameraPermissionString = Manifest.permission.CAMERA
                val onNavigateCameraActivity = remember {
                    return@remember {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                CameraActivity::class.java
                            )
                        )
                    }
                }
                var showCameraDialog by rememberSaveable { mutableStateOf(false) }
                var shouldShowCameraRationale by rememberSaveable {
                    mutableStateOf(true)
                }
                val cameraPermission =
                    rememberPermissionState(cameraPermissionString) { isGranted ->
                        if (!isGranted) {
                            shouldShowCameraRationale =
                                shouldShowRequestPermissionRationale(cameraPermissionString)
                        } else {
                            showCameraDialog = false
                            onNavigateCameraActivity()
                        }
                    }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        CameraFloatingActionButton(
                            onClick = {
                                if (!cameraPermission.status.isGranted) {
                                    showCameraDialog = true
                                } else {
                                    onNavigateCameraActivity()
                                }
                            },
                            isVisible = lazyGridState.isScrollUp()
                        )
                    }
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = GalleryGridScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<GalleryGridScreen> {
                            Gallery(
                                modifier = Modifier.fillMaxSize(),
                                navController = navController,
                                lazyGridState = lazyGridState,
                                showCameraPermissionDialog = showCameraDialog,
                                shouldShowCameraRationale = shouldShowCameraRationale,
                                onRequestCameraPermission = cameraPermission::launchPermissionRequest,
                                onDismissCameraPermissionDialog = { showCameraDialog = false }
                            )
                        }
                        composable<ImageScreen> {
                            val route = it.toRoute<ImageScreen>()
                            val imagePagerViewModel: ImagePagerViewModel = koinViewModel()
                            val images by imagePagerViewModel.images.collectAsStateWithLifecycle()
                            ImagePagerScreen(
                                modifier = Modifier.fillMaxSize(),
                                images = images,
                                initialPage = route.imageIndex
                            )
                        }
                    }
                }
            }
        }
    }
}
