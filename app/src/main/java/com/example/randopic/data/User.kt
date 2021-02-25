package com.example.randopic.data

import com.squareup.moshi.Json

data class User(
    val id: String,
    @Json(name = "updated_at") val updatedAt: String?,
    val username: String,
    val name: String,
    @Json(name = "first_name") val firstName: String?,
    @Json(name = "last_name") val lastName: String?,
    @Json(name = "twitter_username") val twitterUserName: String?,
    @Json(name = "portfolio_url") val portfolioUrl: String?,
    val bio: String?,
    val location: String?,
    val links: UserLinks?,
    @Json(name = "profile_image") val profileImage: ProfileImage?,
    @Json(name = "instagram_username") val instagramUserName: String?,
    @Json(name = "total_collections") val totalCollections: Int?,
    @Json(name = "total_likes") val totalLikes: Int?,
    @Json(name = "total_photos") val totalPhotos: Int?,
    @Json(name = "accepted_tos") val acceptedTos: Boolean?
    )
