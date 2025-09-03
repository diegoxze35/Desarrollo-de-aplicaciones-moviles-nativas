package com.mobile.fragmentsapplication

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mobile.fragmentsapplication.databinding.ActivityMainBinding
import com.mobile.fragmentsapplication.model.Operation
import com.mobile.fragmentsapplication.viewmodel.CalculatorViewModel
import kotlin.getValue

class MainActivity : AppCompatActivity() {
	
	private lateinit var binding: ActivityMainBinding
	private val calculatorViewModel by viewModels<CalculatorViewModel>()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		val navView: BottomNavigationView = binding.navView
		
		val navController = findNavController(R.id.nav_host_fragment_activity_main)
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		val appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.navigation_home,
				R.id.navigation_dashboard,
				R.id.navigation_notifications,
				R.id.navigation_progress_bar,
				R.id.navigation_checkbox
			)
		)
		setupActionBarWithNavController(navController, appBarConfiguration)
		navView.setupWithNavController(navController)
		calculatorViewModel.calculate(1.0, 1.0, Operation.ADD)
	}
}