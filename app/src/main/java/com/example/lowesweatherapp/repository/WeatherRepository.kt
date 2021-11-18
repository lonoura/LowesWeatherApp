package com.example.weatherapp.repository

import android.content.Context
import android.util.Log
import com.example.lowesweatherapp.R
import com.example.lowesweatherapp.model.ApiResponse
import com.example.lowesweatherapp.network.WeatherService
import com.example.lowesweatherapp.utils.ApiState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    @ApplicationContext private val context: Context

) {
    fun getCityWeather(city: String) =

        flow {
            emit(ApiState.Loading)
            val state = try {
                val response = weatherService.getCityWeather(city)
                if (response.isSuccessful && response.body() != null) {
                    if (response.body()!!.list!!.isNotEmpty()) {
                        ApiState.Success(response.body()!!)
                    } else {
                        ApiState.Empty
                    }
                } else {
                    ApiState.Error(context.getString(R.string.failed_to_fetch_city_weather))
                }
            } catch (ex: Exception) {
                ApiState.Error(ex.message ?: context.getString(R.string.an_unexpected_error_occurred))
            }
            emit(state)
        }


    companion object {
        const val TAG = "WEATHER REPO"
    }

}














