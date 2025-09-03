package com.example.myexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myexample.ui.route.Button
import com.example.myexample.ui.route.Calculator
import com.example.myexample.ui.route.Color
import com.example.myexample.ui.route.Image
import com.example.myexample.ui.route.Destinations
import com.example.myexample.ui.route.List
import com.example.myexample.ui.screen.ButtonScreen
import com.example.myexample.ui.screen.CalculatorScreen
import com.example.myexample.ui.screen.ImageScreen
import com.example.myexample.ui.screen.ColorScreen
import com.example.myexample.ui.screen.ListScreen
import com.example.myexample.ui.theme.MyExampleTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyExampleTheme {
				val navController = rememberNavController()
				Scaffold(
					modifier = Modifier.fillMaxSize(),
					bottomBar = {
						val startDestination = Destinations.CALCULATOR
						var selectedDestination by rememberSaveable {
							mutableIntStateOf(
								startDestination.ordinal
							)
						}
						NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
							Destinations.entries.forEachIndexed { index, destination ->
								NavigationBarItem(
									selected = selectedDestination == index,
									onClick = {
										navController.navigate(route = destination.route)
										selectedDestination = index
									},
									icon = {
										Icon(
											imageVector = destination.icon,
											contentDescription = stringResource(destination.labelId)
										)
									},
									label = { Text(stringResource(destination.labelId)) }
								)
							}
						}
					}
				) { innerPadding ->
					NavHost(
						modifier = Modifier
							.fillMaxSize()
							.padding(innerPadding),
						navController = navController,
						startDestination = Calculator
					) {
						composable<Calculator> {
							CalculatorScreen(modifier = Modifier.fillMaxSize())
						}
						composable<Color> {
							ColorScreen(modifier = Modifier.fillMaxSize())
						}
						composable<Image> {
							ImageScreen(modifier = Modifier.fillMaxSize())
						}
						composable<List> {
							ListScreen(modifier = Modifier.fillMaxSize())
						}
						composable<Button> {
							ButtonScreen(modifier = Modifier.fillMaxSize())
						}
					}
				}
			}
		}
	}
}
