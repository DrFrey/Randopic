package com.example.randopic.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.randopic.PictureApi
import com.example.randopic.PicturePagingSource
import com.example.randopic.data.Picture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {
    /*private val _picture = MutableLiveData<Picture>()
    val picture: LiveData<Picture>
        get() = _picture

    private val _picturesPage = MutableLiveData<List<Picture>>()
    val picturesPage: LiveData<List<Picture>>
        get() = _picturesPage*/

    //private val _randomPicturesList = MutableStateFlow<PagingData<Picture>>()
    var randomPicturesList: Flow<PagingData<Picture>>? = null
    //get() = _randomPicturesList

    init {
        getListOfRandomPictures()
    }

    fun getListOfRandomPictures() {
        try {
            //_randomPicturesList.value = PictureApi.retrofitService.getListOfRandomPics()
            randomPicturesList = Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { PicturePagingSource(PictureApi.retrofitService) }
            ).flow
                .cachedIn(viewModelScope)
            Log.d("___pic", "Pager loaded")
        } catch (e: Exception) {
            Log.d("___pic", e.message.toString())
        }
    }
/*    fun getRandomPicture() {
        viewModelScope.launch {
            try {
                _picture.value = PictureApi.retrofitService.getRandomPicture()
                Log.d("___pic", _picture.value.toString())
            } catch (e: Exception) {
                Log.d("___pic", e.message.toString())
            }
        }
    }

    fun getPicturesPage() {
        viewModelScope.launch {
            try {
                _picturesPage.value = PictureApi.retrofitService.getPicturesPage()
                Log.d("___pic", "Page loaded")
            } catch (e: Exception) {
                Log.d("___pic", e.message.toString())
            }
        }
    }*/
}