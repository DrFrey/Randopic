package com.example.randopic.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Position(
    val latitude: String?,
    val longitude: String?
) : Parcelable