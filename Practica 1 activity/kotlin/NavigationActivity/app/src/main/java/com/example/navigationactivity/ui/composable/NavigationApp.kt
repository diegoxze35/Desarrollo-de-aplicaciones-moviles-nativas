package com.example.navigationactivity.ui.composable

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationactivity.AnotherActivity
import com.example.navigationactivity.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationApp(
	isDarkTheme: Boolean,
	onThemeChange: () -> Unit
) {
	val navController = rememberNavController()
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route
	
	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text(getScreenTitle(currentRoute)) },
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.primaryContainer
				),
				actions = {
					IconButton(onClick = onThemeChange) {
						Icon(
							imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.ModeNight,
							contentDescription = null
						)
					}
					val context = LocalContext.current
					IconButton(onClick = {
						context.startActivity(Intent(context, AnotherActivity::class.java))
					}) {
						Icon(
							imageVector = Icons.Default.Navigation,
							contentDescription = null
						)
					}
				}
			)
		},
		bottomBar = {
			NavigationBar {
				NavigationBarItem(
					icon = {
						Icon(
							Icons.Default.Home,
							contentDescription = stringResource(R.string.cd_home)
						)
					},
					label = {
						Text(
							text = stringResource(R.string.nav_home),
							overflow = TextOverflow.Ellipsis
						)
					},
					selected = currentRoute == "home",
					onClick = {
						navController.navigate("home") {
							popUpTo(navController.graph.startDestinationId)
							launchSingleTop = true
						}
					}
				)
				NavigationBarItem(
					icon = { Icon(Icons.Default.Edit, contentDescription = null) },
					label = {
						Text(
							text = stringResource(R.string.nav_text_fields),
							overflow = TextOverflow.Ellipsis
						)
					},
					selected = currentRoute == "textFields",
					onClick = {
						navController.navigate("textFields") {
							popUpTo(navController.graph.startDestinationId)
							launchSingleTop = true
						}
					}
				)
				NavigationBarItem(
					icon = { Icon(Icons.Default.TouchApp, contentDescription = null) },
					label = {
						Text(
							text = stringResource(R.string.nav_buttons),
							maxLines = 1
						)
					},
					selected = currentRoute == "buttons",
					onClick = {
						navController.navigate("buttons") {
							popUpTo(navController.graph.startDestinationId)
							launchSingleTop = true
						}
					}
				)
				NavigationBarItem(
					icon = { Icon(Icons.Default.CheckCircle, contentDescription = null) },
					label = {
						Text(
							text = stringResource(R.string.nav_selection),
							maxLines = 1
						)
					},
					selected = currentRoute == "selection",
					onClick = {
						navController.navigate("selection") {
							popUpTo(navController.graph.startDestinationId)
							launchSingleTop = true
						}
					}
				)
				NavigationBarItem(
					icon = { Icon(Icons.AutoMirrored.Default.List, contentDescription = null) },
					label = {
						Text(
							text = stringResource(R.string.nav_lists),
							maxLines = 1
						)
					},
					selected = currentRoute == "lists",
					onClick = {
						navController.navigate("lists") {
							popUpTo(navController.graph.startDestinationId)
							launchSingleTop = true
						}
					}
				)
				NavigationBarItem(
					icon = { Icon(Icons.Default.Info, contentDescription = null) },
					label = {
						Text(
							text = stringResource(R.string.nav_info),
							maxLines = 1
						)
					},
					selected = currentRoute == "information",
					onClick = {
						navController.navigate("information") {
							popUpTo(navController.graph.startDestinationId)
							launchSingleTop = true
						}
					}
				)
			}
		}
	) { innerPadding ->
		NavHost(
			navController = navController,
			startDestination = "home",
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding)
		) {
			composable("home") { HomeScreen() }
			composable("textFields") { TextFieldsScreen() }
			composable("buttons") { ButtonsScreen() }
			composable("selection") { SelectionScreen() }
			composable("lists") { ListsScreen() }
			composable("information") { InformationScreen() }
		}
	}
}

// Helper function to get screen titles
@Composable
fun getScreenTitle(route: String?): String {
	return when (route) {
		"home" -> stringResource(R.string.title_ui_elements_demo)
		"textFields" -> stringResource(R.string.title_text_fields)
		"buttons" -> stringResource(R.string.title_buttons)
		"selection" -> stringResource(R.string.title_selection)
		"lists" -> stringResource(R.string.title_lists)
		"information" -> stringResource(R.string.title_information)
		else -> stringResource(R.string.title_ui_elements_demo)
	}
}