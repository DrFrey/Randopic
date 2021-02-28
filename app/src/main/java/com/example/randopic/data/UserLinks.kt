package com.example.randopic.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserLinks(
    val self: String?,
    val html: String?,
    val photos: String?,
    val likes: String?,
    val portfolio: String?,
    val following: String?,
    val followers: String?
) : Parcelable