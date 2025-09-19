package com.example.navigationactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.navigationactivity.ui.composable.NavigationApp
import com.example.navigationactivity.ui.theme.NavigationActivityTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			var isDarkTheme by rememberSaveable { mutableStateOf(false) }
			NavigationActivityTheme(darkTheme = isDarkTheme) {
				NavigationApp(
					isDarkTheme = isDarkTheme,
					onThemeChange = { isDarkTheme = !isDarkTheme }
				)
			}
		}
	}
}
