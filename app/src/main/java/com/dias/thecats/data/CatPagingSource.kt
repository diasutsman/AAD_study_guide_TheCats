package com.dias.thecats.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

private const val CAT_STARTING_PAGE_INDEX = 0

class CatPagingSource(
    private val catApi: CatApi,
) : PagingSource<Int, Cat>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        val position = params.key ?: CAT_STARTING_PAGE_INDEX

        val cats = catApi.getCatImages(position, params.loadSize)
        Log.d("CatPagingSource", "load: $cats")

        val nextKey = if(cats.isEmpty()) {
            null
        } else {
            position + 1
        }
        return try {

            LoadResult.Page(
                data = cats,
                prevKey = if(position == CAT_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
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