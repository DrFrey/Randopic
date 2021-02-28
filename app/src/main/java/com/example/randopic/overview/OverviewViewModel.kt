package com.example.randopic.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.randopic.PictureApi
import com.example.randopic.PictureFilterValues
import com.example.randopic.PicturePagingSource
import com.example.randopic.data.Picture
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class OverviewViewModel : ViewModel() {

    var picturesList: Flow<PagingData<Picture>>? = null

    init {
        getPictures(PictureFilterValues.RANDOM)
    }

    fun getPictures(filter: PictureFilterValues) {
        try {
            picturesList = Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { PicturePagingSource(PictureApi.retrofitService, filter) }
            ).flow
                .cachedIn(viewModelScope)
            Log.d("___pic", "Pager loaded + ${filter.value}; ${picturesList.toString()}")
        } catch (e: Exception) {
            Log.d("___pic", e.message.toString())
        }
    }

    private val _navigateToSelectedPicture = MutableLiveData<Picture>()
    val navigateToSelectedPicture: LiveData<Picture>
        get() = _navigateToSelectedPicture

    fun displayPictureDetails(picture: Picture) {
        _navigateToSelectedPicture.value = picture
    }

    fun displayPictureDetailsComplete() {
        _navigateToSelectedPicture.value = null
    }

}