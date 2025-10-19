package com.damm.artspace.ui.common.repository

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class ImageChangeNotifier {
    private val _dataNeedsRefresh = MutableSharedFlow<Unit>(replay = 0)
    val dataNeedsRefresh = _dataNeedsRefresh.asSharedFlow()

    suspend fun notifyDataChanged() {
        _dataNeedsRefresh.emit(Unit)
    }
}
