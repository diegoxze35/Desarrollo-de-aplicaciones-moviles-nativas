package com.mobile.fragmentsapplication.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.fragmentsapplication.databinding.HistoryFragmentBinding
import com.mobile.fragmentsapplication.ui.history.recyclerview.HistoryAdapter
import com.mobile.fragmentsapplication.viewmodel.CalculatorViewModel

class HistoryFragment : Fragment() {
	
	private var _binding: HistoryFragmentBinding? = null
	private val viewModel by activityViewModels<CalculatorViewModel>()
	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = HistoryFragmentBinding.inflate(inflater, container, false)
		val root: View = binding.root
		return root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val customAdapter = HistoryAdapter(viewModel.state.value.history)
		binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
		binding.recyclerView.adapter = customAdapter
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}