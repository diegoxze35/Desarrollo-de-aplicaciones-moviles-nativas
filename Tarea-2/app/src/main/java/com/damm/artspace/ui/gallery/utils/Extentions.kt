package com.damm.artspace.ui.gallery.utils

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun LazyGridState.isScrollUp(): Boolean {
	var previousIndex by remember(this) { mutableIntStateOf(firstVisibleItemIndex) }
	var previousScrollOffset by remember(this) { mutableIntStateOf(firstVisibleItemScrollOffset) }
	return remember(this) {
		derivedStateOf {
			(when {
				previousIndex != firstVisibleItemIndex -> previousIndex > firstVisibleItemIndex
				else -> previousScrollOffset >= firstVisibleItemScrollOffset
			}).also {
				previousIndex = firstVisibleItemIndex
				previousScrollOffset = firstVisibleItemScrollOffset
			}
		}
	}.value
}
