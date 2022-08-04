package com.dias.thecats.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dias.thecats.data.Cat
import com.dias.thecats.data.CatImagesRepository

private const val PAGE_SIZE = 10

class MainViewModel(repository: CatImagesRepository) : ViewModel() {

//    private val _catImages = MutableLiveData<List<Cat>?>()
//    val catImages = _catImages as LiveData<List<Cat>?>
//
//    fun loadImages() = repository.getCatImages(
//        onSuccess = {
//            _catImages.value = it
//        },
//        onFailure = {
//            _catImages.value = null
//        }
//    )

    val catImages: LiveData<PagingData<Cat>> = repository.getCatStream()
        .asLiveData(viewModelScope.coroutineContext)
        .cachedIn(viewModelScope)
}