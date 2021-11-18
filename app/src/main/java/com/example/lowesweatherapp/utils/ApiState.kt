package com.example.lowesweatherapp.utils


sealed class ApiState<out T> {
    object Loading : ApiState<Nothing>()
    object Empty: ApiState<Nothing>()
    data class Success<T>(val data: T) : ApiState<T>()
    data class Error(val msg: String) : ApiState<Nothing>()
}