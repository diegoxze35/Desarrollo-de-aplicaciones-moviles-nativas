package com.damm.artspace.di

import com.damm.artspace.repository.ImageRepository
import com.damm.artspace.repository.impl.ImageRepositoryImpl
import com.damm.artspace.ui.viewmodel.GalleryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<ImageRepository> {
	    ImageRepositoryImpl(context = get())
    }
    viewModelOf(::GalleryViewModel)
}