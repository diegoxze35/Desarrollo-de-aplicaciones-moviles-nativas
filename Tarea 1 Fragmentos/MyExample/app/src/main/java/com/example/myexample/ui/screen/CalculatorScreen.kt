package com.example.myexample.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myexample.R
import com.example.myexample.domain.Operation
import com.example.myexample.ui.theme.MyExampleTheme
import kotlin.math.pow

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
	val buttons = Operation.entries.toList()
	var a by rememberSaveable { mutableStateOf(String()) }
	var b by rememberSaveable { mutableStateOf(String()) }
	var result by rememberSaveable { mutableDoubleStateOf(0.0) }
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		TextField(
			modifier = Modifier.fillMaxWidth(fraction = 0.9f),
			value = a,
			onValueChange = { a = it },
			label = {
				Text(text = stringResource(R.string.number, "A"))
			},
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Next,
				keyboardType = KeyboardType.Decimal
			)
		)
		TextField(
			modifier = Modifier.fillMaxWidth(fraction = 0.9f),
			value = b,
			onValueChange = { b = it },
			label = {
				Text(text = stringResource(R.string.number, "B"))
			},
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Done,
				keyboardType = KeyboardType.Decimal
			)
		)
		LazyVerticalGrid(
			modifier = Modifier.fillMaxWidth(),
			columns = GridCells.Adaptive(120.dp)
		) {
			items(buttons) {
				Card(
					modifier = Modifier
						.aspectRatio(1f)
						.padding(all = 12.dp)
						.clickable(role = Role.Button) {
							val (a, b) = (a.toDoubleOrNull() ?: 0.0) to (b.toDoubleOrNull() ?: 0.0)
							result = when (it) {
								Operation.ADD -> a + b
								Operation.SUB -> a - b
								Operation.MUL -> a * b
								Operation.DIV -> a / b
								Operation.POW -> a.pow(b)
								Operation.MOD -> a % b
							}
						}
				) {
					Box(modifier = Modifier.fillMaxSize()) {
						Text(
							modifier = Modifier.align(Alignment.Center),
							text = it.sign,
							style = MaterialTheme.typography.titleLarge
						)
					}
				}
			}
		}
		Text(text = "$result")
	}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CalculatorPreview() {
	MyExampleTheme {
		Scaffold {
			CalculatorScreen(
				modifier = Modifier
					.fillMaxSize()
					.padding(it)
			)
		}
	}
}