package com.damm.artspace.ui.canvas.navigation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun BrushControls(
	color: Color,
	onColorChange: (Color) -> Unit,
	strokeWidth: Float,
	onStrokeWidthChange: (Float) -> Unit,
	modifier: Modifier = Modifier
) {
    val colors = listOf(Color.Black, Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta, Color.White)

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        // Selector de color
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            colors.forEach { colorOption ->
                ColorCircle(
                    color = colorOption,
                    isSelected = color == colorOption,
                    onClick = { onColorChange(colorOption) }
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        // Selector de grosor
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Grosor:", modifier = Modifier.padding(end = 8.dp))
            Slider(
                value = strokeWidth,
                onValueChange = onStrokeWidthChange,
                valueRange = 5f..50f,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${strokeWidth.toInt()}",
                modifier = Modifier.padding(start = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}
