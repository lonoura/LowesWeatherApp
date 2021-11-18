package com.example.lowesweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lowesweatherapp.model.ApiResponse
import com.example.lowesweatherapp.model.City
import com.example.lowesweatherapp.model.HourlyWeather
import com.example.lowesweatherapp.utils.ApiState
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    private val _cityWeather = MutableLiveData<ApiState<ApiResponse>>()
    val cityWeather: LiveData<ApiState<ApiResponse>>
        get() = _cityWeather

    private var navigateToDetailsScreen = MutableLiveData<Boolean>()

    var selectedWeather: HourlyWeather? = null

    var cityName: String? = null

    fun fetchCityWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.getCityWeather(city).collect { weatherState ->
                if (weatherState is ApiState.Success) {
                    cityName = weatherState.data.city.name
                    navigateToDetailsScreen.postValue(true)
                    _cityWeather.postValue(weatherState)
                }
            }
        }
    }

    companion object {
        private const val TAG = "View Model"
    }

}