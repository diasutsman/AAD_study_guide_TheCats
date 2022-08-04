package com.dias.thecats.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.dias.thecats.R

object BindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun imageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView)
            .load(url)
            .placeholder(CircularProgressDrawable(imageView.context).apply {
                strokeWidth = 5f
                centerRadius = 30f
                start()
            })
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }
}