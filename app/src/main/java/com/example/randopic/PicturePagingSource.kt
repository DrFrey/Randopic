package com.example.randopic

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.randopic.data.Picture
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1
private const val ITEMS_COUNT = 10

class PicturePagingSource(
    private val service: PictureApiService
) : PagingSource<Int, Picture>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Picture> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getListOfRandomPics(ITEMS_COUNT, position)
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + (params.loadSize / ITEMS_COUNT)
            }
            LoadResult.Page(
                    data = response,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position-1,
                    nextKey =nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Picture>): Int? {
        TODO("Not yet implemented")
    }
}