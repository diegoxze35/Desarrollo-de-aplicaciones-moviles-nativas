package com.mobile.fragmentsapplication.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CalculatorState(
	val result: Double = 0.0,
	val history: List<String> = emptyList()
) : Parcelable
