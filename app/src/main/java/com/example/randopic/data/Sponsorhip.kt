package com.example.randopic.data

import com.squareup.moshi.Json

data class Sponsorhip(
    @Json(name = "impression_urls") val impressionUrls: List<String>?,
    val tagline: String?,
    @Json(name = "tagline_url") val taglineUrl: String?,
    val sponsor: User?
)
