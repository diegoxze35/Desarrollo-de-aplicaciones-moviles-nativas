package com.damm.artspace.ui.gallery.navigation.composable

import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.damm.artspace.R
import com.damm.artspace.ui.canvas.activity.CanvasActivity

@Composable
fun NavigateCanvasButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(modifier = modifier, onClick = {
        context.startActivity(Intent(context, CanvasActivity::class.java))
    }) {
        Icon(Icons.Default.Create, contentDescription = null)
        Text(text = stringResource(R.string.canvas))
    }
}
