package com.dias.thecats.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatImagesRepository {
    fun catPagingSource() = CatPagingSource(CatApi.getApiService())
}
