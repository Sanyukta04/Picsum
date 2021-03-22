package com.example.myapplication.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageId: Long) {
    Glide.with(view.context)
        .load("https://picsum.photos/300/300?image=$imageId")
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(view)

}