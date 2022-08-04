package com.dias.thecats.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dias.thecats.data.CatImagesRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelProvider : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(CatImagesRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}