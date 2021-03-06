package com.example.randopic.details

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.randopic.PictureApi
import com.example.randopic.data.Picture
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailsViewModel(
    pictureId: String,
    application: Application
) : AndroidViewModel(application) {
    private val _picture = MutableLiveData<Picture>()
    val picture: LiveData<Picture>
        get() = _picture

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String>
        get() = _errorMsg

    private val _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean>
        get() = _result


    init {
        getPictureById(pictureId)
    }

    fun getPictureById(pictureId: String) = viewModelScope.launch {
        try {
            _picture.value = PictureApi.retrofitService.getPictureById(pictureId)
            _result.value = true
        } catch (e: Exception) {
            _result.value = false
            _errorMsg.value = e.localizedMessage
        }
    }

    private val _navigateToOverview = MutableLiveData<Boolean>()
    val navigateToOverview: LiveData<Boolean>
        get() = _navigateToOverview

    fun goBack() {
        _navigateToOverview.value = true
    }

    fun goBackComplete() {
        _navigateToOverview.value = null
    }

    val pictureHeight = _picture.value?.height

}