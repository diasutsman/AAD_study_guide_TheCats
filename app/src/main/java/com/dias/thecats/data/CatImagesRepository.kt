package com.dias.thecats.data

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dias.thecats.R
import kotlinx.coroutines.flow.Flow
import java.util.*

class CatImagesRepository(private val api: CatApi, private val context: Context) {
    fun getCatStream() : Flow<PagingData<Cat>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = PAGE_SIZE
        ),
        pagingSourceFactory = { CatPagingSource(api) }
    ).flow
    companion object {
        private const val PAGE_SIZE = 10
    }

    fun downloadImage(url: String) {
        Log.d("CatImagesRepository", "downloadImage: $url")
        val request = DownloadManager.Request(Uri.parse(url))
            // allow wifi and mobile data download
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setTitle(context.getString(R.string.txt_download))
            .setDescription("Downloading the file...")
            .setAllowedOverRoaming(false)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                Date().time.toString() + ".png"
            )

        // get download service and enqueue file
        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }
}
