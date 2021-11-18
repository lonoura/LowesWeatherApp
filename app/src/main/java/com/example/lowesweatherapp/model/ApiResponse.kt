package com.example.lowesweatherapp.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    val city: City,
    val cnt: Int?,
    val cod: String?,
    val list: List<HourlyWeather>?,
    val message: Int?
)