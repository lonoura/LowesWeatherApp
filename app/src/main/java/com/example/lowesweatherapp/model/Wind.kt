package com.example.lowesweatherapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    val deg: Int?,
    val gust: Double?,
    val speed: Double?
)