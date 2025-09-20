package com.damm.artspace.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.damm.artspace.ui.navigation.route.ImageGrid
import com.damm.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
	        ArtSpaceTheme {
		        Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
			        val navController = rememberNavController()
			        NavHost(
				        navController = navController,
				        startDestination = ImageGrid,
				        modifier = Modifier.Companion.padding(innerPadding)
			        ) {
				        composable<ImageGrid> {
					        var showDialog by remember { mutableStateOf(false) }
					        if (showDialog) {
						        AlertDialog(
							        onDismissRequest = { showDialog = false },
							        confirmButton = {
								        Button(onClick = {
									        showDialog = false
								        }) {
									        Text("Confirm")
								        }
							        },
							        title = { Text("Dialog Title") },
							        text = { Text("Dialog Text") }
						        )
					        } else {
						        Box(modifier = Modifier.Companion.fillMaxSize()) {
							        Button(
								        modifier = Modifier.Companion.align(Alignment.Companion.TopCenter),
								        onClick = { showDialog = true }
							        ) {
								        Text("Show Dialog")
							        }
						        }
					        }
				        }
			        }
		        }
	        }
        }
    }
}