package com.dias.thecats.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dias.thecats.R

object BindingAdapters {

    @BindingAdapter("imageUrl", "dontTransform", requireAll = false)
    @JvmStatic
    fun imageUrl(imageView: ImageView, imageUrl: String?, dontTransform: Boolean = false) {
        Glide.with(imageView)
            .load(imageUrl)
            .placeholder(CircularProgressDrawable(imageView.context).apply {
                strokeWidth = 5f
                centerRadius = 30f
                start()
            })
            .error(R.drawable.ic_broken_image)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .apply {
                if (dontTransform) dontTransform()
            }
            .into(imageView)
    }
}