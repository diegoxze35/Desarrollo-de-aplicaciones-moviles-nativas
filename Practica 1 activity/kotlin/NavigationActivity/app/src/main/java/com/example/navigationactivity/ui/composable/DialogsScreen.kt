package com.example.navigationactivity.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DialogsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        var showAlertDialog by remember { mutableStateOf(false) }
        if (showAlertDialog) {
            AlertDialog(
                onDismissRequest = { showAlertDialog = false },
                title = { Text("Dialog Title") },
                text = { Text("Dialog Content") },
                confirmButton = {
                    Button(onClick = { showAlertDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }
        Button(onClick = { showAlertDialog = true }) {
            Text("Show Dialog")
        }
    }
}