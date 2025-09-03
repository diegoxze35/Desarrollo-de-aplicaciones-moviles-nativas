package com.mobile.fragmentsapplication.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mobile.fragmentsapplication.model.Operation
import com.mobile.fragmentsapplication.model.RESULT

class CalculatorViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
	
	val state = savedStateHandle.getStateFlow(RESULT, CalculatorState())
	
	fun calculate(a: Double, b: Double, operation: Operation) {
		val (result, sing) = when (operation) {
			Operation.ADD -> a + b to '+'
			Operation.SUBTRACTION -> a - b to '-'
			Operation.MULTIPLICATION -> a * b to '×'
			Operation.DIVISION -> a / b to '÷'
		}
		savedStateHandle[RESULT] = CalculatorState(
			result = result,
			history = state.value.history + "$a $sing $b = ${"%.2f".format(result)}"
		)
	}
	
}