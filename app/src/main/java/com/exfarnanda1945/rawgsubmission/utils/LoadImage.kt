package com.exfarnanda1945.rawgsubmission.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.exfarnanda1945.rawgsubmission.R

fun loadImage(context:Context,img:String?,view:ImageView,drawable:Int? = null){
    val sourceImage = img ?: drawable
    Glide.with(context)
        .load(sourceImage)
        .placeholder( R.drawable.imgplaceholder)
        .into(view)
}