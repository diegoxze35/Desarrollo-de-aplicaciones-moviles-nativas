package com.damm.artspace.ui.gallery.navigation

import kotlinx.serialization.Serializable

@Serializable
object PermissionsScreen

@Serializable
object GalleryGridScreen

@Serializable
data class ImageScreen(
    val id: Long,
    val name: String,
    val uri: String
)
