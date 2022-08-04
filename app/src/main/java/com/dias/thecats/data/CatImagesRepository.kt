package com.dias.thecats.data

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.dias.thecats.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CatImagesRepository(private val context: Context) {
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
