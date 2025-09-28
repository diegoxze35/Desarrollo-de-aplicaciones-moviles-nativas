package com.damm.artspace.ui.common.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private const val THEME_KEY = "current_theme"
private const val DATASTORE_NAME = "settings"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)
val IS_DARK_THEME_KEY = booleanPreferencesKey(THEME_KEY)
