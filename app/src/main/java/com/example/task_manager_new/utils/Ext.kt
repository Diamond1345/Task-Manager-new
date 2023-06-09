package com.example.task_manager_new.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.task_manager_new.R

fun ImageView.loadImage(image: String?){
    Glide.with(this).load(image).placeholder(R.drawable.ic_profile).into(this)
}
