package com.example.lowesweatherapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadWeatherImage(imgPath: String) {
    Glide.with(context).load("https://openweathermap.org/img/w/$imgPath.png").centerCrop()
        .into(this)
}