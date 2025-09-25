package com.damm.artspace.ui.gallery.navigation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.damm.artspace.R
import com.damm.artspace.domain.gallery.Image
import com.damm.artspace.ui.domain.TopAppBarState

private const val PREFETCH_DISTANCE = 5

@Composable
internal fun GalleryGridScreen(
	modifier: Modifier,
	onConfigureTopAppBar: (TopAppBarState) -> Unit,
	lazyGridState: LazyGridState,
	images: List<Image>,
	onLoadNextPage: () -> Unit,
	onImageClick: (Int) -> Unit
) {
	
	LaunchedEffect(images.size) {
		onConfigureTopAppBar(
			TopAppBarState(title = {
				Column {
					Text(
						text = stringResource(R.string.gallery),
						style = MaterialTheme.typography.titleLarge
					)
					Text(
						text = pluralStringResource(
							id = R.plurals.galley_images,
							count = images.size,
							images.size
						),
						style = MaterialTheme.typography.titleSmall
					)
				}
			})
		)
	}
	
	val reachedEnd by remember {
		derivedStateOf {
			with(lazyGridState.layoutInfo) {
				visibleItemsInfo.lastOrNull()?.let {
					it.index >= totalItemsCount - PREFETCH_DISTANCE
				} ?: false
			}
		}
	}
	
	LaunchedEffect(reachedEnd) {
		if (reachedEnd) {
			onLoadNextPage()
		}
	}
	
	LazyVerticalGrid(
		state = lazyGridState,
		modifier = modifier,
		columns = GridCells.Adaptive(minSize = 120.dp),
		contentPadding = PaddingValues(all = 8.dp)
	) {
		itemsIndexed(
			items = images,
			key = { index, image -> image.id + index + image.dateAddedTime }
		) { index, image ->
			AsyncImage(
				model = image.uri,
				contentDescription = image.name,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.aspectRatio(1f)
					.clickable { onImageClick(index) }
			)
		}
	}
}
