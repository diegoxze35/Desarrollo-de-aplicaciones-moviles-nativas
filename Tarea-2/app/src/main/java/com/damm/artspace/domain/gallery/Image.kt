package com.damm.artspace.domain.gallery

import android.net.Uri

data class Image(val id: Long, val name: String, val uri: Uri, val dateAddedTime: Int)