package com.damm.artspace.di

import com.damm.artspace.data.gallery.ImageRepository
import com.damm.artspace.data.gallery.impl.MediaStoreImageRepository
import com.damm.artspace.ui.gallery.navigation.grid.viewmodel.GalleryGridViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single<ImageRepository> {
	    MediaStoreImageRepository(context = get())
    }

    viewModelOf(constructor = ::GalleryGridViewModel)

}
