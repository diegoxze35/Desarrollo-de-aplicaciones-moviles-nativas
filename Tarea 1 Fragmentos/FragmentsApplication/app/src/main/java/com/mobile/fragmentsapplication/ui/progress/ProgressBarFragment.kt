package com.mobile.fragmentsapplication.ui.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mobile.fragmentsapplication.databinding.FragmentProgressBarBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class ProgressBarFragment : Fragment() {
	
	private var _binding: FragmentProgressBarBinding? = null
	
	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		_binding = FragmentProgressBarBinding.inflate(inflater, container, false)
		binding.horizontalProgressBar.min = 0
		binding.horizontalProgressBar.max = 100
		val root: View = binding.root
		return root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewLifecycleOwner.lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				while (true) {
					binding.horizontalProgressBar.setProgress(50, true)
					delay(500L)
					binding.horizontalProgressBar.setProgress(100, true)
					delay(500L)
					binding.horizontalProgressBar.setProgress(0, true)
					delay(500L)
				}
			}
		}
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
	
}