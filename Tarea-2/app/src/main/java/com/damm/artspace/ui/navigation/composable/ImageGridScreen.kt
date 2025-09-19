package com.damm.artspace.ui.navigation.composable
/*

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun ImageGridScreen(
    images: List<Int>,
    isLoading: Boolean
) {
    */
/*val images by viewModel.images.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()*//*


    val permissionState = rememberPermissionState(
        permission = Manifest.permission.READ_MEDIA_IMAGES
    )

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    if (permissionState.status.isGranted) {
        LaunchedEffect(Unit) {
            viewModel.loadImages()
        }

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(images) { image ->
                    Image(
                        painter = rememberAsyncImagePainter(image.uri),
                        contentDescription = image.name,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(4.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Se necesita permiso para acceder a la galería.")
        }
    }
}*/
