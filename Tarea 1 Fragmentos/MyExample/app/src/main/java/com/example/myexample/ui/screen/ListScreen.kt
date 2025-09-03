package com.example.myexample.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListScreen(modifier: Modifier = Modifier) {
    val items = remember { (1..30).map { index ->
        "Item $index" to mutableStateOf(false)
    } }

    LazyColumn(modifier = modifier) {
        items(items.size) { index ->
            val (text, state) = items[index]
            val isEven = index % 2 == 1
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (state.value) "$text ✅" else "$text ❌",
                    style = MaterialTheme.typography.bodyLarge
                )
                
                if (isEven) {
                    Checkbox(
                        checked = state.value,
                        onCheckedChange = { state.value = it }
                    )
                } else {
                    Switch(
                        checked = state.value,
                        onCheckedChange = { state.value = it }
                    )
                }
            }
        }
    }
}