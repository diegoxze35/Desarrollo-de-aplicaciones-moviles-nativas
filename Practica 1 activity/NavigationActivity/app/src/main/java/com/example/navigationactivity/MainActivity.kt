package com.example.navigationactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.navigationactivity.ui.theme.NavigationActivityTheme
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			NavigationActivityTheme {
				Surface {
					SolarSystemView(modifier = Modifier.fillMaxSize())
				}
			}
		}
	}
}

@Composable
fun SolarSystemView(modifier: Modifier = Modifier) {
	Canvas(modifier = modifier) {
		val center = Offset(size.width / 2f, size.height / 2f)
		
		// Base orbit radius step relative to the smaller screen dimension
		val minDim = minOf(size.width, size.height)
		val baseStep = minDim * 0.045f // spacing between orbits
		
		// Visual sizes for bodies (in pixels)
		val offset = 0f
		val sunRadius = 14.dp.toPx() + offset
		val planetSizes = listOf(
			(3).dp.toPx() + offset,  // Mercury
			(5).dp.toPx() + offset,  // Venus
			(5.5).dp.toPx() + offset,// Earth
			(4.5).dp.toPx() + offset,// Mars
			(10).dp.toPx() + offset, // Jupiter
			(9).dp.toPx() + offset,  // Saturn
			(7).dp.toPx() + offset,  // Uranus
			(7.5).dp.toPx() + offset // Neptune
		)
		
		// Colors for bodies
		val sunColor = Color(0xFFFFD54F)
		val planetColors = listOf(
			Color(0xFFB0BEC5), // Mercury - grey
			Color(0xFFFFCC80), // Venus - pale orange
			Color(0xFF64B5F6), // Earth - blue
			Color(0xFFE57373), // Mars - red
			Color(0xFFFFB74D), // Jupiter - orange
			Color(0xFFF5E6A7), // Saturn - pale yellow
			Color(0xFF80DEEA), // Uranus - cyan
			Color(0xFF64B5F6)  // Neptune - blue
		)
		
		// Draw Sun
		drawCircle(color = sunColor, radius = sunRadius, center = center)
		
		// Angles for positioning planets (fixed for a static scene)
		val angles = listOf(
			15f, 55f, 100f, 160f, 210f, 265f, 310f, 345f
		).map { Math.toRadians(it.toDouble()) }
		
		// Draw orbits and planets
		var orbitRadius = sunRadius + baseStep
		val orbitStroke = Stroke(width = 1.dp.toPx(), cap = StrokeCap.Round)
		
		var earthPosition: Offset? = null
		var saturnPosition: Offset? = null
		
		for (i in 0 until 8) {
			// Orbit
			drawCircle(
				color = Color(0x33FFFFFF),
				radius = orbitRadius,
				center = center,
				style = orbitStroke
			)
			
			// Planet position on its orbit
			val angle = angles[i]
			val px = center.x + orbitRadius * cos(angle).toFloat()
			val py = center.y + orbitRadius * sin(angle).toFloat()
			val planetCenter = Offset(px, py)
			
			// Planet body
			drawCircle(
				color = planetColors[i],
				radius = planetSizes[i],
				center = planetCenter
			)
			
			// Special cases
			if (i == 2) { // Earth index
				earthPosition = planetCenter
			}
			if (i == 5) { // Saturn index
				saturnPosition = planetCenter
			}
			
			orbitRadius += baseStep
		}
		
		// Draw Moon around the Earth (small orbit)
		earthPosition?.let { earth ->
			val moonOrbitRadius = 12.dp.toPx()
			// Moon orbit
			drawCircle(
				color = Color(0x55FFFFFF),
				radius = moonOrbitRadius,
				center = earth,
				style = orbitStroke
			)
			// Moon position (fixed angle)
			val moonAngle = Math.toRadians(230.0)
			val mx = earth.x + moonOrbitRadius * cos(moonAngle).toFloat()
			val my = earth.y + moonOrbitRadius * sin(moonAngle).toFloat()
			drawCircle(
				color = Color.LightGray,
				radius = 2.5.dp.toPx(),
				center = Offset(mx, my)
			)
		}
		
		// Draw a simple ring for Saturn as an extra circle around it
		saturnPosition?.let { saturn ->
			drawCircle(
				color = Color(0x44FFFFFF),
				radius = planetSizes[5] + 6.dp.toPx(),
				center = saturn,
				style = Stroke(width = 1.5.dp.toPx())
			)
		}
	}
}


@PreviewLightDark()
@Composable
fun SolarSystemViewPreview() {
	NavigationActivityTheme {
		Surface {
			SolarSystemView(Modifier.fillMaxSize())
		}
	}
}
