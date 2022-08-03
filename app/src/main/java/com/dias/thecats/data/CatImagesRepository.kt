package com.dias.thecats.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatImagesRepository {
    fun getCatImages(
        onSuccess: (List<Cat>) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        ApiService.getApiService().getCatImages().enqueue(object : Callback<List<Cat>> {
            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                onSuccess(response.body() ?: emptyList())
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                onFailure(t)
            }

        })
    }
}
