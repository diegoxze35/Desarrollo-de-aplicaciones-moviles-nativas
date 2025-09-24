package com.damm.artspace.ui.gallery.navigation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.damm.artspace.R

@Composable
fun CameraFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isVisible: Boolean,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally { it },
        exit = slideOutHorizontally { it }
    ) {
        FloatingActionButton(
            modifier = modifier,
            onClick = onClick,
            shape = CircleShape
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_a_photo_24px),
                contentDescription = stringResource(id = R.string.open_camera)
            )
        }
    }
}
