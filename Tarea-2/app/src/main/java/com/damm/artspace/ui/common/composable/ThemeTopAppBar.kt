package com.damm.artspace.ui.common.composable

import androidx.annotation.DrawableRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.datastore.preferences.core.edit
import com.damm.artspace.R
import com.damm.artspace.ui.common.preferences.IS_DARK_THEME_KEY
import com.damm.artspace.ui.common.preferences.dataStore
import com.damm.artspace.ui.common.state.TopAppBarState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ThemeTopAppBar(
    modifier: Modifier = Modifier,
    topBarState: TopAppBarState,
    isAppInDarkTheme: Boolean
) {
    val coroutineScope = rememberCoroutineScope { Dispatchers.Default }
    val context = LocalContext.current
    TopAppBar(
        modifier = modifier,
        title = topBarState.title,
        actions = {
            topBarState.actions?.invoke(this)
            IconButton(onClick = {
                coroutineScope.launch {
                    context.dataStore.edit {
                        val currentTheme = it[IS_DARK_THEME_KEY] ?: isAppInDarkTheme
                        it[IS_DARK_THEME_KEY] = !currentTheme
                    }
                }
            }) {
                @DrawableRes val painterResource: Int = if (isAppInDarkTheme)
                    R.drawable.light_mode_24px
                else
                    R.drawable.mode_night_24px
                Icon(painter = painterResource(painterResource), contentDescription = null)
            }
        },
        navigationIcon = {
            topBarState.navigationIcon?.invoke()
        }
    )
}
