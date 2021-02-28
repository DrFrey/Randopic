package com.example.randopic.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.randopic.data.Picture

class DetailsViewModel(
    picture: Picture,
    application: Application
) : AndroidViewModel(application) {
    private val _picture = MutableLiveData<Picture>()
    val picture: LiveData<Picture>
        get() = _picture

    init {
        _picture.value = picture
    }
}