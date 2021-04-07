package com.example.randopic.details

import androidx.lifecycle.ViewModel

class InfoDialogViewModel(
    val createdAt: String,
    val views: String,
    val downloads: String,
    val make: String,
    val focalLength: String,
    val aperture: String,
    val exposureTime: String,
    val iso: String,
    val dimensions: String
) : ViewModel() {
}