package com.damm.artspace.ui.canvas.navigation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.damm.artspace.R
import com.damm.artspace.ui.common.state.TopAppBarState

@Composable
internal fun DrawingListScreen(
    modifier: Modifier = Modifier,
    onConfigureTopAppBar: (TopAppBarState) -> Unit,
    onLoadDrawings: () -> Unit,
    drawings: List<ImageBitmap>,
) {

    LaunchedEffect(Unit) {
        onConfigureTopAppBar(TopAppBarState(title = { Text(stringResource(R.string.canvas_list)) }))
        onLoadDrawings()
    }

    if (drawings.isEmpty()) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Text(
                stringResource(R.string.no_drawings),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(drawings) { bitmap ->
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier.aspectRatio(1f)
                ) {
                    Image(
                        bitmap = bitmap,
                        contentDescription = stringResource(R.string.saved_draw),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
