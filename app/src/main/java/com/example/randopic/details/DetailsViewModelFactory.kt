package com.example.randopic.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randopic.data.Picture
import java.lang.IllegalArgumentException

class DetailsViewModelFactory(
    private val picture: Picture,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(picture, application) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }

}