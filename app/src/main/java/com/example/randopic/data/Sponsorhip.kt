package com.example.randopic.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sponsorhip(
    @Json(name = "impression_urls") val impressionUrls: List<String>?,
    val tagline: String?,
    @Json(name = "tagline_url") val taglineUrl: String?,
    val sponsor: User?
) : Parcelable