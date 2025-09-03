package com.mobile.fragmentsapplication.ui.history.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.fragmentsapplication.databinding.TextRowItemBinding

class HistoryAdapter(private val dataSet: List<String>) :
	RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
	
	/**
	 * Provide a reference to the type of views that you are using
	 * (custom ViewHolder)
	 */
	class ViewHolder(private val binding: TextRowItemBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(item: String) {
			binding.textView.text = item
		}
	}
	
	// Create new views (invoked by the layout manager)
	override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
		// Create a new view, which defines the UI of the list item
		val binding =
			TextRowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
		
		return ViewHolder(binding)
	}
	
	// Replace the contents of a view (invoked by the layout manager)
	override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
		viewHolder.bind(dataSet[position])
	}
	
	// Return the size of your dataset (invoked by the layout manager)
	override fun getItemCount() = dataSet.size
	
}