package com.dias.thecats.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dias.thecats.data.Cat
import com.dias.thecats.data.CatImagesRepository

class MainViewModel(private val repository: CatImagesRepository) : ViewModel() {

    private val _catImages = MutableLiveData<List<Cat>?>()
    val catImages = _catImages as LiveData<List<Cat>?>

    fun loadImages() = repository.getCatImages(
        onSuccess = {
            _catImages.value = it
        },
        onFailure = {
            _catImages.value = null
        }
    )
}