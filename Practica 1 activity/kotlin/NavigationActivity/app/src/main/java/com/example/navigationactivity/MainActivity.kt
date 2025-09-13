package com.example.navigationactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.navigationactivity.ui.composable.NavigationApp
import com.example.navigationactivity.ui.theme.NavigationActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationActivityTheme {
                NavigationApp()
            }
        }
    }
}
