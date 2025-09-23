package com.damm.artspace.ui.gallery.activity

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.damm.artspace.ui.gallery.navigation.GalleryGridScreen
import com.damm.artspace.ui.gallery.navigation.ImageScreen
import com.damm.artspace.ui.gallery.navigation.grid.composable.GalleryGridScreen
import com.damm.artspace.ui.gallery.navigation.grid.composable.PermissionRequestScreen
import com.damm.artspace.ui.gallery.navigation.grid.state.GalleryGridState
import com.damm.artspace.ui.gallery.navigation.grid.viewmodel.GalleryGridViewModel
import com.damm.artspace.ui.gallery.theme.ArtSpaceTheme
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = GalleryGridScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<GalleryGridScreen> {
                            val permission =
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                                    Manifest.permission.READ_MEDIA_IMAGES
                                else
                                    Manifest.permission.READ_EXTERNAL_STORAGE
                            var shouldShowRationale by rememberSaveable {
                                mutableStateOf(shouldShowRequestPermissionRationale(permission))
                            }
                            val readImagesPermission =
                                rememberPermissionState(permission) { isGranted ->
                                    if (!isGranted)
                                        shouldShowRationale =
                                            shouldShowRequestPermissionRationale(permission)
                                }
                            if (readImagesPermission.status.isGranted) {
                                val galleryGridViewModel: GalleryGridViewModel = koinViewModel()
                                val uiState by galleryGridViewModel.uiState.collectAsStateWithLifecycle()
                                when (val state = uiState) {
                                    GalleryGridState.Loading -> {
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator()
                                        }
                                        galleryGridViewModel.loadNextPage()
                                    }

                                    is GalleryGridState.Error -> Text(text = state.message)
                                    is GalleryGridState.Success -> GalleryGridScreen(
                                        modifier = Modifier.fillMaxSize(),
                                        images = state.images,
                                        onLoadNextPage = galleryGridViewModel::loadNextPage,
                                        onImageClick = { index ->
                                        }
                                    )
                                }
                            } else {
                                PermissionRequestScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    shouldShowRationale = shouldShowRationale,
                                    onLaunchPermissionRequest = readImagesPermission::launchPermissionRequest
                                )
                            }
                        }
                        composable<ImageScreen> {}
                    }
                }
            }
        }
    }
}
