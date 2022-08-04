package com.dias.thecats.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dias.thecats.data.Cat
import com.dias.thecats.data.CatImagesRepository

class CatViewModel(private val repository: CatImagesRepository) : ViewModel() {

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

    fun downloadImage(url: String?) {
        if (url == null) return
        repository.downloadImage(url)
    }

    class Provider(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CatViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CatViewModel(CatImagesRepository(context)) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}