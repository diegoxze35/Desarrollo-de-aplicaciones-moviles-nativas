package com.damm.artspace.ui.gallery.navigation.composable

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.damm.artspace.domain.gallery.Image
import com.damm.artspace.ui.domain.TopAppBarState
import java.util.Date
import kotlin.math.absoluteValue

@Composable
fun ImagePagerScreen(
    modifier: Modifier,
    onConfigureTopAppBar: (TopAppBarState) -> Unit,
    images: List<Image>,
    initialPage: Int
) {
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { images.size }
    )
    LaunchedEffect(pagerState) {
        onConfigureTopAppBar(
            TopAppBarState(title = {
                Column {
                    Text(
                        text = images[pagerState.currentPage].name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${pagerState.currentPage + 1}/${images.size}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            })
        )
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

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }) {
            val image = images[page]
            AsyncImage(
                modifier = Modifier.align(Alignment.Center),
                model = image.uri,
                contentDescription = image.name,
                contentScale = ContentScale.Fit
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .height(80.dp)
                    .background(Color.Black.copy(alpha = 0.5f)),
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
