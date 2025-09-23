package com.damm.artspace.di

import com.damm.artspace.data.gallery.CachedImageRepository
import com.damm.artspace.data.gallery.ImageRepository
import com.damm.artspace.data.gallery.impl.CachedImageRepositoryImpl
import com.damm.artspace.data.gallery.impl.MediaStoreImageRepository
import com.damm.artspace.ui.gallery.navigation.grid.viewmodel.GalleryGridViewModel
import com.damm.artspace.ui.gallery.navigation.grid.viewmodel.ImagePagerViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single<ImageRepository> {
	    MediaStoreImageRepository(context = get())
    }

    single<CachedImageRepository> {
        CachedImageRepositoryImpl()
    }

    viewModelOf(constructor = ::GalleryGridViewModel)
    viewModelOf(constructor = ::ImagePagerViewModel)

}
