package com.example.randopic.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val title: String?,
    val name: String?,
    val city: String?,
    val country: String?,
    val position: Position?
) : Parcelable
