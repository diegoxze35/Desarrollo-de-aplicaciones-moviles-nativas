package com.damm.artspace.ui.common.state

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.damm.artspace.R

data class TopAppBarState(
    val title: (@Composable () -> Unit) = { Text(stringResource(R.string.app_name)) },
    val actions: (@Composable RowScope.() -> Unit)? = null,
    val navigationIcon: (@Composable () -> Unit)? = null
)