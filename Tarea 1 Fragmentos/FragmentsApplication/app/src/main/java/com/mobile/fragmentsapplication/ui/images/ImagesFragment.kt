package com.mobile.fragmentsapplication.ui.images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobile.fragmentsapplication.R
import com.mobile.fragmentsapplication.databinding.FragmentNotificationsBinding

class ImagesFragment : Fragment() {
	
	private var _binding: FragmentNotificationsBinding? = null
	
	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		
		_binding = FragmentNotificationsBinding.inflate(inflater, container, false)
		val root: View = binding.root
		return root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
			when (checkedId) {
				binding.radioAdd.id -> binding.image.setImageResource(R.drawable.add)
				binding.radioSubtract.id -> binding.image.setImageResource(R.drawable.subtraction)
				binding.radioMultiply.id -> binding.image.setImageResource(R.drawable.multiply)
				binding.radioDivide.id -> binding.image.setImageResource(R.drawable.division)
			}
		}
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}