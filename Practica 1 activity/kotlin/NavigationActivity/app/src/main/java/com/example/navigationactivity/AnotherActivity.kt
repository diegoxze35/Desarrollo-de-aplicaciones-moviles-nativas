package com.example.navigationactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.Square
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationactivity.ui.composable.AnimationsScreen
import com.example.navigationactivity.ui.composable.DialogsScreen
import com.example.navigationactivity.ui.theme.NavigationActivityTheme

class AnotherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationActivityTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        Icons.Default.Animation,
                                        contentDescription = stringResource(R.string.anim)
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(R.string.anim),
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                selected = currentRoute == "animations",
                                onClick = {
                                    navController.navigate("animations") {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Square, contentDescription = null) },
                                label = {
                                    Text(
                                        text = stringResource(R.string.dialog),
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                                selected = currentRoute == "dialogs",
                                onClick = {
                                    navController.navigate("dialogs") {
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
                        startDestination = "animations",
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        composable("animations") {
                            AnimationsScreen()
                        }
                        composable("dialogs") {
                            DialogsScreen()
                        }
                    }
                }
            }
        }
    }
}