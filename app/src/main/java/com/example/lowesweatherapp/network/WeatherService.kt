package com.example.lowesweatherapp.network

import com.example.lowesweatherapp.model.ApiResponse
import com.example.lowesweatherapp.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/forecast")
    suspend fun getCityWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") units: String = "imperial"
    ) : Response<ApiResponse>

}