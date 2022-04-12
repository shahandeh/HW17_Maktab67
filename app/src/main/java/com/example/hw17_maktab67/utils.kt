package com.example.hw17_maktab67

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.glide(url: String){
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.loading_animation)
        .into(this)
}