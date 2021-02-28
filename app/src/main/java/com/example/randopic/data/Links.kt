package com.example.randopic.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Links(
    val self: String?,
    val html: String?,
    val download: String?,
    @Json(name = "download_location") val downloadLocation: String?
) : Parcelable