package com.example.randopic.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class InfoDialogViewModelFactory(
    private val createdAt: String,
    private val views: String,
    private val downloads: String,
    private val make: String,
    private val focalLength: String,
    private val aperture: String,
    private val exposureTime: String,
    private val iso: String,
    private val dimensions: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoDialogViewModel::class.java)) {
            return InfoDialogViewModel(createdAt, views, downloads, make, focalLength, aperture, exposureTime, iso, dimensions) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}