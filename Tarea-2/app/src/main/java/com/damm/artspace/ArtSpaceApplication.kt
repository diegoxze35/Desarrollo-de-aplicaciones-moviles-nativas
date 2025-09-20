package com.damm.artspace

import android.app.Application
import com.damm.artspace.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ArtSpaceApplication : Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger()
			androidContext(this@ArtSpaceApplication)
			modules(appModule)
		}
	}
}