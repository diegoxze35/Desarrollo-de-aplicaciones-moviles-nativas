package com.damm.artspace.ui.gallery.navigation.permissions

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.damm.artspace.R

sealed class RequiredPermission(
    @StringRes val permanentlyDeniedFeatureId: Int,
    @StringRes val rationaleTextId: Int,
    @DrawableRes val rationaleIconId: Int
) {
    data object ReadImagesPermission : RequiredPermission(
        permanentlyDeniedFeatureId = R.string.media_storage,
        rationaleTextId = R.string.permission_rationale_gallery,
        rationaleIconId = R.drawable.photo_24px
    )
    data object CameraPermission : RequiredPermission(
        permanentlyDeniedFeatureId = R.string.camera,
        rationaleTextId = R.string.permission_rationale_camera,
        rationaleIconId = R.drawable.photo_camera_24px
    )
}
