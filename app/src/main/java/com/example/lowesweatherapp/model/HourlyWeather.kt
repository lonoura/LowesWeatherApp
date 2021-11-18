package com.example.lowesweatherapp.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyWeather (
    val clouds: Clouds?,
    val dt: Int?,
    val dtTxt: String?,
    val main: Main,
    val pop: Double?,
    val rain: Rain?,
    val sys: Sys?,
    val visibility: Int?,
    val weather: List<Weather>,
    val wind: Wind?
)