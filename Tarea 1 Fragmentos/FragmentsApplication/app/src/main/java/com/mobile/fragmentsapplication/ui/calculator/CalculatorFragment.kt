package com.mobile.fragmentsapplication.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mobile.fragmentsapplication.databinding.CalculatorFragmentBinding
import com.mobile.fragmentsapplication.model.Operation
import com.mobile.fragmentsapplication.viewmodel.CalculatorViewModel
import kotlinx.coroutines.launch

class CalculatorFragment : Fragment() {
	
	private var _binding: CalculatorFragmentBinding? = null
	private val viewModel by activityViewModels<CalculatorViewModel>()
	
	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = CalculatorFragmentBinding.inflate(inflater, container, false)
		val root: View = binding.root
		return root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.add.setOnClickListener {
			viewModel.calculate(
				a = binding.editTextFirst.text.toString().ifEmpty { "0.0" }.toDouble(),
				b = binding.editTextSecond.text.toString().ifEmpty { "0.0" }.toDouble(),
				operation = Operation.ADD
			)
		}
		binding.minus.setOnClickListener {
			viewModel.calculate(
				a = binding.editTextFirst.text.toString().ifEmpty { "0.0" }.toDouble(),
				b = binding.editTextSecond.text.toString().ifEmpty { "0.0" }.toDouble(),
				operation = Operation.SUBTRACTION
			)
		}
		binding.times.setOnClickListener {
			viewModel.calculate(
				a = binding.editTextFirst.text.toString().ifEmpty { "0.0" }.toDouble(),
				b = binding.editTextSecond.text.toString().ifEmpty { "0.0" }.toDouble(),
				operation = Operation.MULTIPLICATION
			)
		}
		binding.div.setOnClickListener {
			viewModel.calculate(
				a = binding.editTextFirst.text.toString().ifEmpty { "0.0" }.toDouble(),
				b = binding.editTextSecond.text.toString().ifEmpty { "0.0" }.toDouble(),
				operation = Operation.DIVISION
			)
		}
		viewLifecycleOwner.lifecycleScope.launch {
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.state.collect {
					binding.result.text = "${it.result}"
				}
			}
		}
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}