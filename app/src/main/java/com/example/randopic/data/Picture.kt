package com.example.randopic.data

import com.squareup.moshi.Json

data class Picture(
    val id: String,
    @Json(name = "created_at") val createdAt: String?,
    @Json(name = "updated_at") val updatedAt: String?,
    @Json(name = "promoted_at") val promotedAt: String?,
    val width: Int,
    val height: Int,
    val color: String?,
    @Json(name = "blur_hash") val blurHash: String?,
    val description: String?,
    @Json(name = "alt_description") val altDescription: String?,
    val urls: Urls?,
    val links: Links?,
    val categories: List<String>?,
    val likes: Int?,
    @Json(name = "liked_by_user") val likedByUser: Boolean?,
    @Json(name = "current_user_collections") val currentUserCollections: List<String>?,
    val sponsorship: Sponsorhip?,
    val user: User?,
    val exif: Exif?,
    val location: Location?,
    val views: Int?,
    val downloads: Int?
)
