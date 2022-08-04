package com.dias.thecats.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatImagesRepository(private val api: CatApi) {
    fun getCatStream() : Flow<PagingData<Cat>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            maxSize = PAGE_SIZE * 3,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { CatPagingSource(api) }
    ).flow
    companion object {
        private const val PAGE_SIZE = 10
    }
}
