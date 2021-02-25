package com.example.randopic

import com.example.randopic.data.Picture
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.unsplash.com/"
private const val ACCESS_KEY = "Cyc3Z9xSnyATUbouOZUwi4LYvCKFDlqdBL8SKe8ZXxA"
private const val CLIENT_PARAM = "?client_id=" + ACCESS_KEY


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PictureApiService {
    @GET("photos/random/" + CLIENT_PARAM)
    suspend fun getRandomPicture() : Picture

    @GET("photos/random/" + CLIENT_PARAM)
    suspend fun getListOfRandomPics(@Query("count") count: Int = 10, @Query("page") page: Int = 1) : List<Picture>

    @GET("photos/" + CLIENT_PARAM)
    suspend fun getPicturesPage() : List<Picture>
}

object PictureApi {
    val retrofitService: PictureApiService by lazy {
        retrofit.create(PictureApiService::class.java)
    }
}