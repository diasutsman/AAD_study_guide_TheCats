package com.dias.thecats.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dias.thecats.data.Cat
import com.dias.thecats.data.CatImagesRepository

private const val PAGE_SIZE = 10

class MainViewModel(private val repository: CatImagesRepository) : ViewModel() {

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

    val catImages: LiveData<PagingData<Cat>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE,
        ),
        pagingSourceFactory = { repository.catPagingSource() }
    ).flow.cachedIn(viewModelScope).asLiveData()
}