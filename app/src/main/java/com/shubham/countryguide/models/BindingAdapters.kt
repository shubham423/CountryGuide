package com.shubham.countryguide.models

import android.app.Activity
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou


object BindingAdapters {
    @BindingAdapter("app:imageThumb")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: String) {
        GlideToVectorYou.justLoadImage(imageView.context as Activity, Uri.parse(imageUrl) , imageView)
    }
}