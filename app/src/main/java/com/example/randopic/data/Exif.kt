package com.example.randopic.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exif(
    val make: String?,
    val model: String?,
    @Json(name = "exposure_time") val exposureTime: String?,
    val aperture: String?,
    @Json(name = "focal_length") val focalLength: String?,
    val iso: Int?
) : Parcelable
