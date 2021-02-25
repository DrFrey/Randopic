package com.example.randopic.data

import com.squareup.moshi.Json

data class Exif(
    val make: String?,
    val model: String?,
    @Json(name = "exposure_time") val exposureTime: String?,
    val aperture: String?,
    @Json(name = "focal_length") val focalLength: String?,
    val iso: Int?
)
