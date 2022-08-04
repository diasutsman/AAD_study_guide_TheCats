package com.dias.thecats.ui

import android.content.Context
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dias.thecats.data.Cat
import com.dias.thecats.data.CatApi
import com.dias.thecats.data.CatImagesRepository
import kotlinx.coroutines.flow.Flow

class CatViewModel(private val repository: CatImagesRepository) : ViewModel() {
    val catImagesFlow: Flow<PagingData<Cat>> = repository.getCatStream().cachedIn(viewModelScope)

    fun downloadImage(url: String?) {
        if (url == null) return
        repository.downloadImage(url)
    }

    class Provider(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CatViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CatViewModel(CatImagesRepository(CatApi.getApiService(), context)) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}