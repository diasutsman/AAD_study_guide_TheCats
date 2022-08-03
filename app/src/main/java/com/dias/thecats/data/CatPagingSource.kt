package com.dias.thecats.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

private const val START_KEY = 0

class CatPagingSource(
    private val catApi: CatApi,
) : PagingSource<Int, Cat>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val start = params.key ?: START_KEY

        val data = catApi.getCatImages()
        Log.d("CatPagingSource", "load: $data")
        return try {

            LoadResult.Page(
                data = data,
                prevKey = when (start) {
                    START_KEY -> null
                    else -> start - 1
                },
                nextKey = start + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}