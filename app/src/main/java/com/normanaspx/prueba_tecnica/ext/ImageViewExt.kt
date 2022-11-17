package com.normanaspx.prueba_tecnica.ext

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


/**
Created by Norman on 11/16/2022
 **/
object ImageViewExt {

    fun ImageView.toGlide(view: View, src: String, imageView: ImageView){
        Glide.with(view)
            .load(src)
            .fitCenter()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}