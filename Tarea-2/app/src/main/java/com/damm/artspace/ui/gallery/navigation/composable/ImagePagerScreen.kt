package com.damm.artspace.ui.gallery.navigation.composable

import android.icu.text.SimpleDateFormat
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.damm.artspace.R
import com.damm.artspace.domain.gallery.Image
import com.damm.artspace.ui.domain.TopAppBarState
import kotlinx.coroutines.delay
import java.util.Date
import kotlin.math.absoluteValue

@Composable
internal fun ImagePagerScreen(
	modifier: Modifier,
	onConfigureTopAppBar: (TopAppBarState) -> Unit,
	images: List<Image>,
	onReturnToGalleryGrid: () -> Unit,
	initialPage: Int
) {
	val pagerState = rememberPagerState(
		initialPage = initialPage,
		pageCount = { images.size }
	)
	LaunchedEffect(pagerState) {
		onConfigureTopAppBar(
			TopAppBarState(
				title = {
					Column(modifier = Modifier.padding(end = 16.dp)) {
						Text(
							text = images[pagerState.currentPage].name,
							style = MaterialTheme.typography.titleMedium,
							maxLines = 1,
							overflow = TextOverflow.Ellipsis
						)
						Text(
							text = "${pagerState.currentPage + 1}/${images.size}",
							style = MaterialTheme.typography.bodySmall
						)
					}
				},
				navigationIcon = {
					IconButton(onClick = onReturnToGalleryGrid) {
						Icon(
							Icons.AutoMirrored.Filled.ArrowBack,
							stringResource(R.string.go_back)
						)
					}
				}
			)
		)
	}
	var isDateVisible by rememberSaveable { mutableStateOf(true) }
	LaunchedEffect(isDateVisible) {
		if (isDateVisible) {
			delay(3000L)
			isDateVisible = false
		}
	}
	val formatter = remember { SimpleDateFormat.getInstance() }
	HorizontalPager(modifier = modifier, state = pagerState) { page ->
		Box(
			modifier = Modifier
				.fillMaxHeight()
				.graphicsLayer {
					// Calculate the absolute offset for the current page from the
					// scroll position. We use the absolute value which allows us to mirror
					// any effects for both directions
					val pageOffset = (
							(pagerState.currentPage - page) + pagerState
								.currentPageOffsetFraction
							).absoluteValue
					
					// We animate the alpha, between 30% and 100%
					alpha = lerp(
						start = 0.3f,
						stop = 1f,
						fraction = 1f - pageOffset.coerceIn(0f, 1f)
					)
				}
				.clickable(
					role = Role.Image,
					onClick = { isDateVisible = true })
		) {
			val image = images[page]
			AsyncImage(
				modifier = Modifier
					.fillMaxSize()
					.align(Alignment.Center),
				model = image.uri,
				contentDescription = image.name,
				contentScale = ContentScale.Fit
			)
			AnimatedVisibility(
				modifier = Modifier.align(Alignment.TopCenter),
				visible = isDateVisible,
				enter = slideInVertically { -it },
				exit = slideOutVertically { -it }
			) {
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.background(Color.Black.copy(alpha = 0.5f))
						.height(80.dp)
						.padding(horizontal = 24.dp),
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					Icon(
						imageVector = Icons.Default.DateRange,
						contentDescription = null
					)
					Text(
						text = formatter.format(Date(image.dateAddedTime * 1_000L)),
						style = MaterialTheme.typography.titleLarge
					)
				}
			}
		}
	}
	
}
