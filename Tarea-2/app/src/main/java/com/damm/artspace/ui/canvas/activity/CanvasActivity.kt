package com.damm.artspace.ui.canvas.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.damm.artspace.R
import com.damm.artspace.ui.canvas.navigation.Drawing
import com.damm.artspace.ui.canvas.navigation.DrawingList
import com.damm.artspace.ui.canvas.navigation.composable.DrawingListScreen
import com.damm.artspace.ui.canvas.navigation.composable.DrawingScreen
import com.damm.artspace.ui.canvas.navigation.viewmodel.CanvasListViewModel
import com.damm.artspace.ui.canvas.navigation.viewmodel.DrawingViewModel
import com.damm.artspace.ui.canvas.theme.ArtSpaceTheme
import com.damm.artspace.ui.common.composable.ThemeTopAppBar
import com.damm.artspace.ui.common.preferences.IS_DARK_THEME_KEY
import com.damm.artspace.ui.common.preferences.dataStore
import com.damm.artspace.ui.common.state.TopAppBarState
import kotlinx.coroutines.flow.map
import org.koin.androidx.compose.koinViewModel

class CanvasActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val preferences = dataStore.data.map { it[IS_DARK_THEME_KEY] ?: false }
            val selectedTheme by preferences.collectAsStateWithLifecycle(initialValue = false)
            var topBarState by remember { mutableStateOf(TopAppBarState()) }
            ArtSpaceTheme(darkTheme = selectedTheme) {
                var fabVisible by rememberSaveable { mutableStateOf(true) }
                val navController = rememberNavController()
                Scaffold(
                    topBar = { ThemeTopAppBar(topBarState = topBarState, isAppInDarkTheme = selectedTheme) },
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        if (fabVisible) {
                            FloatingActionButton(onClick = { navController.navigate(Drawing) }) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = stringResource(R.string.new_canvas)
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = DrawingList
                    ) {
                        composable<DrawingList> {
                            fabVisible = true
                            val canvasListViewModel: CanvasListViewModel = koinViewModel()
                            val drawings by canvasListViewModel.drawings.collectAsStateWithLifecycle()
                            DrawingListScreen(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(all = 4.dp),
                                onConfigureTopAppBar = { topBarState = it },
                                onLoadDrawings = canvasListViewModel::loadDrawings,
                                drawings = drawings
                            )
                        }
                        composable<Drawing> {
                            fabVisible = false
                            val drawingViewModel: DrawingViewModel = koinViewModel()
                            val canvasState by drawingViewModel.canvasState.collectAsStateWithLifecycle()
                            DrawingScreen(
                                modifier = Modifier.fillMaxSize(),
                                onConfigureTopAppBar = { topBarState = it },
                                onReturnToCanvasList = { navController.popBackStack() },
                                canvasState = canvasState,
                                onSaveBitmap = {
                                    drawingViewModel.saveDrawing(it)
                                    navController.popBackStack()
                                },
                                onUndoLastPath = drawingViewModel::undoLastPath,
                                onClearCanvas = drawingViewModel::clearCanvas,
                                onPathStarted = drawingViewModel::startPath,
                                onPathUpdated = drawingViewModel::updatePath,
                                onColorChange = drawingViewModel::setCurrentColor,
                                onStrokeWidthChange = drawingViewModel::setCurrentStrokeWidth
                            )
                        }
                    }
                }
            }
        }
    }
}
